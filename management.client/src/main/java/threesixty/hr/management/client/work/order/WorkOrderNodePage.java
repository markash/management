package threesixty.hr.management.client.work.order;

import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithNodes;
import org.eclipse.scout.rt.client.ui.form.IForm;
import org.eclipse.scout.rt.platform.text.TEXTS;

public class WorkOrderNodePage extends AbstractPageWithNodes {
	
	private final Long id;
	
	public WorkOrderNodePage(
			final Long id) {		
	
		this.id = id;
	}
	
	@Override
	protected String getConfiguredTitle() {
		return TEXTS.get("WorkOrder");
	}
	
	@Override
	protected boolean getConfiguredLeaf() {
		return true;
	}
	
	@Override
	protected boolean getConfiguredTableVisible() {
		return false;
	}
	
	@Override
	protected Class<? extends IForm> getConfiguredDetailForm() {
	  return WorkOrderForm.class;
	}
	
	@Override
	protected void execDetailFormActivated() {
		
		((WorkOrderForm) getDetailForm()).setId(this.id);
		
		super.execDetailFormActivated();
	}
}
