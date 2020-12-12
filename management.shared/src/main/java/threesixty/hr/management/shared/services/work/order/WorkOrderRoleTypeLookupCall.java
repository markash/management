package threesixty.hr.management.shared.services.work.order;

import org.eclipse.scout.rt.shared.services.lookup.ILookupService;
import org.eclipse.scout.rt.shared.services.lookup.LookupCall;

public class WorkOrderRoleTypeLookupCall extends LookupCall<String> {
	private static final long serialVersionUID = -8931483791399073359L;

	@Override
	protected Class<? extends ILookupService<String>> getConfiguredService() {
		return IWorkOrderRoleTypeLookupService.class;
	}
}
