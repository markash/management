package threesixty.hr.management.client.employee;

import java.util.Date;

import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.datefield.AbstractDateField;
import org.eclipse.scout.rt.client.ui.form.fields.datefield.AbstractTimeField;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.longfield.AbstractLongField;
import org.eclipse.scout.rt.client.ui.form.fields.sequencebox.AbstractSequenceBox;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.ObjectUtility;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;

import threesixty.hr.management.client.employee.TimesheetForm.MainBox.CancelButton;
import threesixty.hr.management.client.employee.TimesheetForm.MainBox.IdField;
import threesixty.hr.management.client.employee.TimesheetForm.MainBox.OkButton;
import threesixty.hr.management.client.employee.TimesheetForm.MainBox.ProjectGroupBox;
import threesixty.hr.management.client.employee.TimesheetForm.MainBox.ProjectGroupBox.PersonField;
import threesixty.hr.management.client.employee.TimesheetForm.MainBox.ProjectGroupBox.ProjectField;
import threesixty.hr.management.client.employee.TimesheetForm.MainBox.ProjectGroupBox.TaskField;
import threesixty.hr.management.client.employee.TimesheetForm.MainBox.TimeGroupBox;
import threesixty.hr.management.client.employee.TimesheetForm.MainBox.TimeGroupBox.DateField;
import threesixty.hr.management.client.employee.TimesheetForm.MainBox.TimeGroupBox.DurationField;
import threesixty.hr.management.client.employee.TimesheetForm.MainBox.TimeGroupBox.TimeField;
import threesixty.hr.management.client.employee.TimesheetForm.MainBox.TimeGroupBox.TimeField.EndTimeField;
import threesixty.hr.management.client.employee.TimesheetForm.MainBox.TimeGroupBox.TimeField.StartTimeField;
import threesixty.hr.management.client.employee.TimesheetForm.MainBox.TimeGroupBox.TimeModeSelectorField;
import threesixty.hr.management.client.field.time.AbstractTimeModeSelectorField;
import threesixty.hr.management.shared.employee.TimesheetFormData;
import threesixty.hr.management.shared.services.IScheduleManager;
import threesixty.hr.management.shared.services.party.person.PersonLookupCall;
import threesixty.hr.management.shared.services.work.order.WorkOrderAssignedToLookupCall;

@FormData(value = TimesheetFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class TimesheetForm extends AbstractForm {
	@Override
	protected String getConfiguredTitle() {
		return TEXTS.get("Timesheet");
	}

	public MainBox getMainBox() {
		return getFieldByClass(MainBox.class);
	}

	public ProjectGroupBox getGroupBox() {
		return getFieldByClass(ProjectGroupBox.class);
	}

	public TimeGroupBox getTimeGroupBox() {
		return getFieldByClass(TimeGroupBox.class);
	}

	public TimeModeSelectorField getTimeModeSelectorField() {
		return getFieldByClass(TimeModeSelectorField.class);
	}
	
	public PersonField getPersonField() {
		return getFieldByClass(PersonField.class);
	}

	public ProjectField getProjectField() {
		return getFieldByClass(ProjectField.class);
	}

	public TaskField getTaskField() {
		return getFieldByClass(TaskField.class);
	}

	public DateField getDateField() {
		return getFieldByClass(DateField.class);
	}

	public StartTimeField getStartTimeField() {
		return getFieldByClass(StartTimeField.class);
	}

	public EndTimeField getEndTimeField() {
		return getFieldByClass(EndTimeField.class);
	}

	public DurationField getDurationField() {
		return getFieldByClass(DurationField.class);
	}

	public TimeField getTimeBox() {
		return getFieldByClass(TimeField.class);
	}

	public IdField getIdField() {
		return getFieldByClass(IdField.class);
	}

	public OkButton getOkButton() {
		return getFieldByClass(OkButton.class);
	}

	public CancelButton getCancelButton() {
		return getFieldByClass(CancelButton.class);
	}


	@Order(1000)
	public class MainBox extends AbstractGroupBox /* NOSONAR*/ {

		
		@Override
		protected int getConfiguredGridColumnCount() { return 2; }

		@Order(1000)
		public class ProjectGroupBox extends AbstractGroupBox /* NOSONAR*/ {

			@Override
			protected String getConfiguredLabel() { return "Project"; }
			
			@Override
			protected int getConfiguredGridColumnCount() { return 1; }
			
			@Override
			protected double getConfiguredGridWeightX() { return 1; }
			
			@Override
			protected int getConfiguredGridW() { return 1; }
	
			@Order(1000)
			public class PersonField extends AbstractSmartField<Long> /* NOSONAR*/ {
				
				@Override
				protected String getConfiguredLabel() { return TEXTS.get("Person"); }
				
				@Override
				protected int getConfiguredGridW() { return 2; }
				
				@Override
				protected byte getConfiguredLabelPosition() { return LABEL_POSITION_TOP; }
				
				@Override
				protected int getConfiguredWidthInPixel() { return 250; }
				
				@Override
				protected Class<? extends ILookupCall<Long>> getConfiguredLookupCall() {
					return PersonLookupCall.class;
				}
			}

			@Order(2000)
			public class ProjectField extends AbstractSmartField<Long> /* NOSONAR*/ {
				
				@Override
				protected String getConfiguredLabel() { return TEXTS.get("Project"); }
				
				@Override
				protected int getConfiguredGridW() { return 2; }
				
				@Override
				protected byte getConfiguredLabelPosition() { return LABEL_POSITION_TOP; }
				
				@Override
				protected void execPrepareLookup(
						final ILookupCall<Long> call) {
					
					call.setRec(getPersonField().getValue());
				}
				
				@Override
				protected Class<? extends ILookupCall<Long>> getConfiguredLookupCall() {
					return WorkOrderAssignedToLookupCall.class;
				}
			}

			@Order(3000)
			public class TaskField extends AbstractSmartField<Long> /* NOSONAR*/ {
				
				@Override
				protected String getConfiguredLabel() { return TEXTS.get("Task"); }
				
				@Override
				protected int getConfiguredGridW() { return 2; }
				
				@Override
				protected byte getConfiguredLabelPosition() { return LABEL_POSITION_TOP; }
			}
		}

		
		
		@Order(1500)
		public class TimeGroupBox extends AbstractGroupBox /* NOSONAR*/ {
			
			@Override
			protected String getConfiguredLabel() {
				return "Time";
			}
			
			@Override
			protected int getConfiguredGridW() { return 1; }
			
			@Override
			protected int getConfiguredGridColumnCount() { return 2; }
			
			@Override
			protected double getConfiguredGridWeightX() { return 0.1; }
			
			@Order(1000)
			public class TimeModeSelectorField extends AbstractTimeModeSelectorField /* NOSONAR*/ {
				
				@Override
				protected int getConfiguredGridW() { return 2; }
				
				@Override
				protected boolean getConfiguredLabelVisible() { return false; }
				
				@Override
				protected boolean getConfiguredFillHorizontal() { return true; }
				
				@Override
				protected void execChangedValue() {
					
					final Long mode = ObjectUtility.nvl(getTimeModeSelectorField().getValue(), 1L);
					
					getTimeBox().setVisible(mode == 1L);
					getStartTimeField().setVisible(mode == 1L);
					getEndTimeField().setVisible(mode == 1L);
					getDurationField().setVisible(mode == 2L);
				}
			}

			@Order(2000)
			public class DateField extends AbstractDateField /* NOSONAR*/ {
				
				@Override
				protected String getConfiguredLabel() { return TEXTS.get("Date"); }
				
				@Override
				protected int getConfiguredGridW() { return 1; }
				
				@Override
				protected byte getConfiguredLabelPosition() { return LABEL_POSITION_TOP; }
				
				@Override
				protected boolean getConfiguredFillHorizontal() { return true; }
			}

			
			@Order(2500)
			public class TimeField extends AbstractSequenceBox /* NOSONAR*/ {
				
				@Override
				protected String getConfiguredLabel() { return TEXTS.get("Time"); }

				@Override
				protected int getConfiguredGridW() { return 1; }
				
				@Override
				protected byte getConfiguredLabelPosition() { return LABEL_POSITION_TOP; }
				
				@Override
				protected boolean getConfiguredFillHorizontal() { return true; }
				
				@Override
				protected boolean getConfiguredAutoCheckFromTo() { return true; }
				
				@Order(3000)
				public class StartTimeField extends AbstractTimeField /* NOSONAR*/ {
					
					@Override
					protected int getConfiguredGridW() { return 1; }
				}

				@Order(4000)
				public class EndTimeField extends AbstractTimeField /* NOSONAR*/ {
					
					@Override
					protected int getConfiguredGridW() { return 1; }
				}
			}

			@Order(5000)
			public class DurationField extends AbstractTimeField /* NOSONAR*/ {
				
				@Override
				protected String getConfiguredLabel() { return TEXTS.get("Duration"); }
				
				@Override
				protected int getConfiguredGridW() { return 1; }
				
				@Override
				protected byte getConfiguredLabelPosition() { return LABEL_POSITION_TOP; }
				
				@Override
				protected int getConfiguredTimePickerResolution() {return 15; }

			}
		}

		public class IdField extends AbstractLongField /* NOSONAR*/ {
			
			@Override
			protected boolean getConfiguredVisible() { return false; }
		}
		
		@Order(2000)
		public class OkButton extends AbstractOkButton /* NOSONAR*/ { }

		@Order(3000)
		public class CancelButton extends AbstractCancelButton /* NOSONAR*/ {}
	}

	public void startModify(
			final Long timesheetId) {
		
		startInternalExclusive(new ModifyHandler(timesheetId));
	}

	public void startNew(
			final Date entryDate) {
		
		startInternal(new NewHandler(entryDate));
	}

	public class NewHandler extends AbstractFormHandler {
		
		public NewHandler(
				final Date entryDate) {
			
			getDateField().setValue(entryDate);
			getTimeModeSelectorField().setValue(1L);
			getTimeModeSelectorField().fireValueChanged();
		}
		
		@Override
		protected void execLoad() {
			
			TimesheetFormData formData = new TimesheetFormData();
			
			exportFormData(formData);
			
			BEANS.get(IScheduleManager.class).prepareNew(formData);
			
			importFormData(formData);
		}

		@Override
		protected void execStore() {
			
			TimesheetFormData formData = new TimesheetFormData();
			exportFormData(formData);
			
			BEANS.get(IScheduleManager.class).record(formData);
		}
	}

	public class ModifyHandler extends AbstractFormHandler {
		
		public ModifyHandler(
				final Long timesheetId) {
		
			getIdField().setValue(timesheetId);
		}
		
		@Override
		protected void execLoad() {

			TimesheetFormData formData = new TimesheetFormData();
			
			exportFormData(formData);
			
			formData = BEANS.get(IScheduleManager.class).prepareModify(formData);
			
			importFormData(formData);
			
			getTimeModeSelectorField().fireValueChanged();
		}

		@Override
		protected void execStore() {
		}
	}
	
}
