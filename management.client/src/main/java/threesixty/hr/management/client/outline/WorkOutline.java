package threesixty.hr.management.client.outline;

import java.util.List;

import org.eclipse.scout.rt.client.ui.desktop.outline.AbstractOutline;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;

import threesixty.hr.management.client.dashboard.DashboardNodePage;
import threesixty.hr.management.client.employee.EmployeeNodePage;
import threesixty.hr.management.client.employee.EmployeeTablePage;
import threesixty.hr.management.client.work.order.WorkOrderTablePage;
import threesixty.hr.management.shared.Icons;

/**
 * @author Mark Ashworth
 */
@Order(1000)
public class WorkOutline extends AbstractOutline {

	@Override
	protected void execCreateChildPages(List<IPage<?>> pageList) {
		super.execCreateChildPages(pageList);
		pageList.add(new WorkOrderTablePage());
		pageList.add(new EmployeeNodePage(35L));
		pageList.add(new EmployeeTablePage());
		pageList.add(new DashboardNodePage());
	}

	@Override
	protected String getConfiguredTitle() {
		return TEXTS.get("Work");
	}

	@Override
	protected String getConfiguredIconId() {
		return Icons.Pencil;
	}
}
