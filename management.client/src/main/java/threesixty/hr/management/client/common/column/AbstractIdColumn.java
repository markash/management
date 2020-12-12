package threesixty.hr.management.client.common.column;

import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractLongColumn;
import org.eclipse.scout.rt.platform.text.TEXTS;

public abstract class AbstractIdColumn extends AbstractLongColumn {
	@Override
	protected String getConfiguredHeaderText() {
		return TEXTS.get("Id");
	}

	@Override
	protected int getConfiguredWidth() {
		return 10;
	}
}