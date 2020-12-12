package threesixty.hr.management.client.dashboard;

import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;

import threesixty.hr.management.client.dashboard.DashboardForm.MainBox.CancelButton;
import threesixty.hr.management.client.dashboard.DashboardForm.MainBox.GroupBox;
import threesixty.hr.management.client.dashboard.DashboardForm.MainBox.OkButton;
import threesixty.hr.management.shared.DashboardFormData;

@FormData(value = DashboardFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class DashboardForm extends AbstractForm {
	@Override
	protected String getConfiguredTitle() {
		// TODO [USER] verify translation
		return TEXTS.get("Dashboard");
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
		}

		@Override
		protected void execStore() {
		}
	}

	public class ModifyHandler extends AbstractFormHandler {
		@Override
		protected void execLoad() {
		}

		@Override
		protected void execStore() {
		}
	}
}
