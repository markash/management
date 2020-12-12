package threesixty.hr.management.shared.services.work.order;

import java.util.List;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;

@TunnelToServer
public interface IWorkOrderRoleTypeResource extends IService {
	
	/**
	 * Retrieve the work order role types
	 * @return List of work order role types
	 */
	List<WorkOrderRoleType> retrieve();
}
