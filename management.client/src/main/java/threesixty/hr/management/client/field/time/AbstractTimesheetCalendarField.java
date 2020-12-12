package threesixty.hr.management.client.field.time;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.eclipse.scout.rt.client.IClientSession;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.action.menu.CalendarMenuType;
import org.eclipse.scout.rt.client.ui.action.menu.IMenu;
import org.eclipse.scout.rt.client.ui.action.menu.IMenuType;
import org.eclipse.scout.rt.client.ui.action.menu.root.ContextMenuEvent;
import org.eclipse.scout.rt.client.ui.action.menu.root.ContextMenuListener;
import org.eclipse.scout.rt.client.ui.action.menu.root.IContextMenu;
import org.eclipse.scout.rt.client.ui.basic.calendar.AbstractCalendar;
import org.eclipse.scout.rt.client.ui.basic.calendar.provider.AbstractCalendarItemProvider;
import org.eclipse.scout.rt.client.ui.desktop.outline.MenuWrapper;
import org.eclipse.scout.rt.client.ui.form.FormEvent;
import org.eclipse.scout.rt.client.ui.form.fields.calendarfield.AbstractCalendarField;
import org.eclipse.scout.rt.client.ui.messagebox.MessageBox;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.CollectionUtility;
import org.eclipse.scout.rt.shared.services.common.calendar.ICalendarItem;

import threesixty.hr.management.client.employee.TimesheetForm;
import threesixty.hr.management.shared.services.IScheduleManager;
import threesixty.hr.management.shared.util.LocalDateUtility;

public abstract class AbstractTimesheetCalendarField extends AbstractCalendarField<AbstractTimesheetCalendarField.Calendar> {
	
	@Override
	protected int getConfiguredGridH() { return 10; }

	@Override
	protected boolean getConfiguredLabelVisible() { return false; }

	public class Calendar extends AbstractCalendar {
		
		private ContextMenuListener contextMenuListener;

        private final List<IMenu> calendarMenus = new ArrayList<>();
        
		@Override
		protected void execInitCalendar() {
			
			contextMenuListener = event -> {
				if (ContextMenuEvent.TYPE_STRUCTURE_CHANGED == event.getType()) {
					IContextMenu rootContextMenu = getForm().getRootGroupBox().getContextMenu();
					rootContextMenu.removeChildActions(calendarMenus);
					calendarMenus.clear();
					
					for (IMenu menu : event.getSource().getChildActions()) {
						calendarMenus.add(MenuWrapper.wrapMenuIfNotWrapped(menu));
					}
					
					rootContextMenu.addChildActions(calendarMenus);
				}
			};
			getCalendar().getContextMenu().addContextMenuListener(contextMenuListener);
		}

		@Override
		protected void execDisposeCalendar() {
			getCalendar().getContextMenu().removeContextMenuListener(contextMenuListener);
		}
        
		@Order(1000)
		public class MyCalendarItemProvider extends AbstractCalendarItemProvider {
			
			@Override
			protected void execLoadItemsInBackground(
					final IClientSession session, 
					final Date minDate, 
					final Date maxDate,
					final Set<ICalendarItem> results) {
				
				/*TODO Resolve the submitter for timesheet */
				results.addAll(BEANS.get(IScheduleManager.class)
						.retrieveAppointments(
								8L, 
								LocalDateUtility.toLocalDate(minDate), 
								LocalDateUtility.toLocalDate(maxDate)));
			}
		}


		@Order(100)
		public class AddTimesheetMenu extends AbstractMenu {
			@Override
			protected String getConfiguredText() {
				return TEXTS.get("AddTimesheetEntry");
			}

			@Override
			protected Set<? extends IMenuType> getConfiguredMenuTypes() {
				return CollectionUtility.hashSet(CalendarMenuType.EmptySpace);
			}

			@Override
			protected void execAction() {
				
				TimesheetForm form = new TimesheetForm();

				form.addFormListener(event -> {
					
					if (event.getType() == FormEvent.TYPE_STORE_AFTER) {
						
						getCalendar().reloadCalendarItems();
					}
				});
				form.startNew(getCalendar().getSelectedDate());
			}
		}
		
		@Order(1000)
		public class EditMenu extends AbstractMenu {
			
			@Override
			protected String getConfiguredText() { return TEXTS.get("Edit"); }

			@Override
			protected Set<? extends IMenuType> getConfiguredMenuTypes() { return CollectionUtility.hashSet(CalendarMenuType.CalendarComponent); }

			@Override
			protected void execAction() {
				
				ICalendarItem item = getCalendar().getSelectedItem(ICalendarItem.class);
				
				if (item == null) {
					
					new MessageBox()
						.withHeader("Unexpected error")
						.withBody("The selected item is null")
						.withYesButtonText("OK")
						.show();
					
					return;
				}
				
				TimesheetForm form = new TimesheetForm();
				
				form.addFormListener(event -> {
					
					if (event.getType() == FormEvent.TYPE_STORE_AFTER) {
						
						getCalendar().reloadCalendarItems();
					}
				});
				
				form.startModify((Long) item.getItemId());
			}
		}
	}
}