package threesixty.hr.management.shared.services.work.order;

import java.util.List;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

import threesixty.hr.management.shared.work.order.WorkOrderTablePageData;

@TunnelToServer
public interface IWorkOrderEngine extends IService {

	List<WorkOrder> retrieve(
				final SearchFilter filter);

	WorkOrderTablePageData retrievePageData(
			final SearchFilter filter);
}
