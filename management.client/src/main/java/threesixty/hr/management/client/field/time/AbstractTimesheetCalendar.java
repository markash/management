package threesixty.hr.management.client.field.time;

import org.eclipse.scout.rt.client.ui.basic.calendar.AbstractCalendar;

public abstract class AbstractTimesheetCalendar extends AbstractCalendar {
	
//	private ContextMenuListener contextMenuListener;
//
//    private final List<IMenu> calendarMenus = new ArrayList<>();
//    
//	@Override
//	protected void execInitCalendar() {
//		
//		contextMenuListener = event -> {
//			if (ContextMenuEvent.TYPE_STRUCTURE_CHANGED == event.getType()) {
//				IContextMenu rootContextMenu = getForm().getRootGroupBox().getContextMenu();
//				rootContextMenu.removeChildActions(calendarMenus);
//				calendarMenus.clear();
//				
//				for (IMenu menu : event.getSource().getChildActions()) {
//					calendarMenus.add(MenuWrapper.wrapMenuIfNotWrapped(menu));
//				}
//				
//				rootContextMenu.addChildActions(calendarMenus);
//			}
//		};
//		getCalendar().getContextMenu().addContextMenuListener(contextMenuListener);
//	}
//
//	@Override
//	protected void execDisposeCalendar() {
//		getCalendar().getContextMenu().removeContextMenuListener(contextMenuListener);
//	}
//    
//	@Order(1000)
//	public class MyCalendarItemProvider extends AbstractCalendarItemProvider {
//		
//		@Override
//		protected void execLoadItemsInBackground(
//				final IClientSession session, 
//				final Date minDate, 
//				final Date maxDate,
//				final Set<ICalendarItem> results) {
//			
//			results.addAll(BEANS.get(IEmployeeCalendarService.class).retrieve(null, minDate, maxDate));
//		}
//	}
//
//
//	@Order(100)
//	public class AddTimesheetMenu extends AbstractMenu {
//		@Override
//		protected String getConfiguredText() {
//			return TEXTS.get("AddTimesheetEntry");
//		}
//
//		@Override
//		protected Set<? extends IMenuType> getConfiguredMenuTypes() {
//			return CollectionUtility.hashSet(CalendarMenuType.EmptySpace);
//		}
//
//		@Override
//		protected void execAction() {
//			
//			TimesheetForm form = new TimesheetForm();
//			
//			System.out.println(getCalendar().getStartHour());
//			form.startNew(getCalendar().getSelectedDate());
//		}
//	}
//	
//	@Order(1000)
//	public class EditMenu extends AbstractMenu {
//		
//		@Override
//		protected String getConfiguredText() { return TEXTS.get("Edit"); }
//
//		@Override
//		protected Set<? extends IMenuType> getConfiguredMenuTypes() { return CollectionUtility.hashSet(CalendarMenuType.CalendarComponent); }
//
//		@Override
//		protected void execAction() {
//			
//			ICalendarItem item = getCalendar().getSelectedItem(ICalendarItem.class);
//			
//			if (item != null) {
//				
//				System.out.println(item);
//			}
//			
//			TimesheetForm form = new TimesheetForm();
//			form.startNew(getCalendar().getSelectedDate());
//		}
//	}
}