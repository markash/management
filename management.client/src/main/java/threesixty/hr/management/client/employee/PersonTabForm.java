package threesixty.hr.management.client.employee;

import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.longfield.AbstractLongField;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.client.ui.form.fields.tabbox.AbstractTabBox;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;

import threesixty.hr.management.client.employee.PersonTabForm.MainBox.CancelButton;
import threesixty.hr.management.client.employee.PersonTabForm.MainBox.GroupBox;
import threesixty.hr.management.client.employee.PersonTabForm.MainBox.IdField;
import threesixty.hr.management.client.employee.PersonTabForm.MainBox.OkButton;
import threesixty.hr.management.client.employee.PersonTabForm.MainBox.TabGroupBox;
import threesixty.hr.management.client.employee.PersonTabForm.MainBox.TabGroupBox.TabBox;
import threesixty.hr.management.client.employee.PersonTabForm.MainBox.TabGroupBox.TabBox.OverviewGroupBox;
import threesixty.hr.management.client.employee.PersonTabForm.MainBox.TabGroupBox.TabBox.OverviewGroupBox.NameField;
import threesixty.hr.management.client.employee.PersonTabForm.MainBox.TabGroupBox.TabBox.OverviewGroupBox.WorkOrderRoleTypeField;
import threesixty.hr.management.client.employee.PersonTabForm.MainBox.TabGroupBox.TabBox.TimesheetGroupBox;
import threesixty.hr.management.client.employee.PersonTabForm.MainBox.TabGroupBox.TabBox.TimesheetGroupBox.TimesheetField;
import threesixty.hr.management.client.field.time.AbstractTimesheetCalendarField;
import threesixty.hr.management.shared.employee.PersonTabFormData;
import threesixty.hr.management.shared.services.employee.IEmployeeManager;
import threesixty.hr.management.shared.services.work.order.WorkOrderRoleTypeLookupCall;

@FormData(value = PersonTabFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class PersonTabForm extends AbstractForm {
	
	public Long getId() {
		return getIdField().getValue();
	}

	public void setId(final Long id) {
		getIdField().setValue(id);
	}
	
	@Override
	protected String getConfiguredTitle() {
		return TEXTS.get("Person");
	}

	public MainBox getMainBox() {
		return getFieldByClass(MainBox.class);
	}

	public GroupBox getGroupBox() {
		return getFieldByClass(GroupBox.class);
	}

	public TabGroupBox getTabGroupBox() {
		return getFieldByClass(TabGroupBox.class);
	}

	public TabBox getTabBox() {
		return getFieldByClass(TabBox.class);
	}

	public OverviewGroupBox getOverviewGroupBox() {
		return getFieldByClass(OverviewGroupBox.class);
	}

	public TimesheetGroupBox getTimesheetGroupBox() {
		return getFieldByClass(TimesheetGroupBox.class);
	}

	public TimesheetField getddField() {
		return getFieldByClass(TimesheetField.class);
	}

	public NameField getTestField() {
		return getFieldByClass(NameField.class);
	}

	public IdField getIdField() {
		return getFieldByClass(IdField.class);
	}

	public WorkOrderRoleTypeField getWorkOrderRoleTypeField() {
		return getFieldByClass(WorkOrderRoleTypeField.class);
	}

	public OkButton getOkButton() {
		return getFieldByClass(OkButton.class);
	}

	public CancelButton getCancelButton() {
		return getFieldByClass(CancelButton.class);
	}

	@Order(1000)
	public class MainBox extends AbstractGroupBox {

		@Order(1000)
		public class GroupBox extends AbstractGroupBox {

		}

		@Order(1500)
		public class TabGroupBox extends AbstractGroupBox {

			@Order(1000)
			public class TabBox extends AbstractTabBox {

				@Order(1000)
				public class OverviewGroupBox extends AbstractGroupBox {
					
					@Override
					protected String getConfiguredLabel() {
						return TEXTS.get("Overview");
					}

					@Order(1000)
					public class NameField extends AbstractStringField {
						@Override
						protected String getConfiguredLabel() { return TEXTS.get("Name"); }

						@Override
						protected int getConfiguredMaxLength() {
							return 128;
						}
					}

					@Order(2000)
					public class WorkOrderRoleTypeField extends AbstractSmartField<String> {
						@Override
						protected String getConfiguredLabel() {
							return TEXTS.get("RoleType");
						}
						
						@Override
						protected Class<? extends ILookupCall<String>> getConfiguredLookupCall() {
							return WorkOrderRoleTypeLookupCall.class;
						}
					}
				}

				@Order(2000)
				public class TimesheetGroupBox extends AbstractGroupBox {
					@Override
					protected String getConfiguredLabel() {
						return TEXTS.get("Timesheet");
					}

					@Order(1000)
					public class TimesheetField extends AbstractTimesheetCalendarField {
						
					}
				}

			}
			
			
		}
		
		
		public class IdField extends AbstractLongField {
			@Override
			protected boolean getConfiguredVisible() { return false; }
		}
		
		@Order(2000)
		public class OkButton extends AbstractOkButton {
			@Override
			protected boolean getConfiguredVisible() { return false; }
		}

		@Order(3000)
		public class CancelButton extends AbstractCancelButton { 
			@Override
			protected boolean getConfiguredVisible() { return false; }
		}
	}
	
	@Override
	protected void execFormActivated() {

		PersonTabFormData formData = new PersonTabFormData();
		
		exportFormData(formData);

		formData = BEANS.get(IEmployeeManager.class).retrieve(formData);
		
		importFormData(formData);
	}
}
