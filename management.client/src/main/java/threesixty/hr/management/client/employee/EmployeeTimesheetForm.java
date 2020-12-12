package threesixty.hr.management.client.employee;

import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;

import threesixty.hr.management.client.employee.EmployeeTimesheetForm.MainBox.CancelButton;
import threesixty.hr.management.client.employee.EmployeeTimesheetForm.MainBox.GroupBox;
import threesixty.hr.management.client.employee.EmployeeTimesheetForm.MainBox.OkButton;

public class EmployeeTimesheetForm extends AbstractForm {
	@Override
	protected String getConfiguredTitle() {
		// TODO [USER] verify translation
		return TEXTS.get("EmployeeTimesheet");
	}

	public MainBox getMainBox() {
		return getFieldByClass(MainBox.class);
	}

	public GroupBox getGroupBox() {
		return getFieldByClass(GroupBox.class);
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

		@Order(2000)
		public class OkButton extends AbstractOkButton {

		}

		@Order(3000)
		public class CancelButton extends AbstractCancelButton {

		}
	}

	public void startModify() {
		startInternalExclusive(new ModifyHandler());
	}

	public void startNew() {
		startInternal(new NewHandler());
	}

	public class NewHandler extends AbstractFormHandler {
		@Override
		protected void execLoad() {
			
			((TimesheetForm) getForm()).getTimeModeSelectorField().setValue(1L);
		}

		@Override
		protected void execStore() {
		}
	}

	public class ModifyHandler extends AbstractFormHandler {
		@Override
		protected void execLoad() {
			
			((TimesheetForm) getForm()).getTimeModeSelectorField().setValue(1L);
		}

		@Override
		protected void execStore() {
		}
	}
}
