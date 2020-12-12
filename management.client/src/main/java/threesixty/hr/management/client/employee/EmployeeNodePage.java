package threesixty.hr.management.client.employee;

import org.eclipse.scout.rt.client.ui.form.IForm;
import org.eclipse.scout.rt.platform.text.TEXTS;

import threesixty.hr.management.client.dashboard.AbstractFormNodePage;

public class EmployeeNodePage extends AbstractFormNodePage {

	private Long id;
	
	public EmployeeNodePage(
			final Long id) {
		
		this.id = id;
	}
	
	@Override
	protected String getConfiguredTitle() {
		return TEXTS.get("Person");
	}

	@Override
	protected Class<? extends IForm> getConfiguredDetailForm() {
	  return PersonTabForm.class;
	}
	
	@Override
	protected void execDetailFormActivated() {
		
		((PersonTabForm) getDetailForm()).setId(this.id);
		
		super.execDetailFormActivated();
	}
}
