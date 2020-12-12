package threesixty.hr.management.shared.services.work.order;

import org.eclipse.scout.rt.shared.services.lookup.ILookupService;
import org.eclipse.scout.rt.shared.services.lookup.LookupCall;

public class WorkOrderAssignedToLookupCall extends LookupCall<Long>{
	private static final long serialVersionUID = -5384183930930528422L;

	@Override
	protected Class<? extends ILookupService<Long>> getConfiguredService() {
		return IWorkOrderAssignedToLookupService.class;
	}
}
