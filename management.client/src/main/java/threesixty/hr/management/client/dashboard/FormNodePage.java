package threesixty.hr.management.client.dashboard;

import java.util.List;

import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.client.ui.form.IForm;

public class FormNodePage extends AbstractFormNodePage {
	
	@Override
	protected void execCreateChildPages(List<IPage<?>> pageList) {
		super.execCreateChildPages(pageList);
	}
	
	protected java.lang.Class<? extends IForm> getConfiguredDetailForm() {
		
		return ChartFieldForm.class;
	};
}
