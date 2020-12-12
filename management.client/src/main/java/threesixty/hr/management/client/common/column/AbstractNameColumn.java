package threesixty.hr.management.client.common.column;

import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.platform.text.TEXTS;

public abstract class AbstractNameColumn extends AbstractStringColumn {
	
	@Override
	protected String getConfiguredHeaderText() {
		return TEXTS.get("Name");
	}

	@Override
	protected int getConfiguredWidth() {
		return 25;
	}
}