package threesixty.hr.management.client.dashboard;

import java.util.List;

import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithNodes;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.client.ui.form.IForm;
import org.eclipse.scout.rt.platform.text.TEXTS;

public class DashboardNodePage extends AbstractPageWithNodes {
	
	protected java.lang.Class<? extends IForm> getConfiguredDetailForm() {
		
		return ChartFieldForm.class;
	};
	
	protected void execInitPage() {
		setTableVisible(false);
	};
	
	protected void ensureDetailFormStarted() {
		getDetailForm().start();
	};
	
	@Override
	protected void execCreateChildPages(List<IPage<?>> pageList) {
		super.execCreateChildPages(pageList);
	}

	@Override
	protected String getConfiguredTitle() {
		// TODO [USER] verify translation
		return TEXTS.get("DashboardNodePage");
	}

}
