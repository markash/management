package threesixty.hr.management.client.dashboard;

import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithNodes;
import org.eclipse.scout.rt.platform.text.TEXTS;

public abstract class AbstractFormNodePage extends AbstractPageWithNodes {
	@Override
	protected String getConfiguredTitle() {
		// TODO [USER] verify translation
		return TEXTS.get("AbstractFormNodePage");
	}
}
