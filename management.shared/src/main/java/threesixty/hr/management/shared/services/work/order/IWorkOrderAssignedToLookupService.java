package threesixty.hr.management.shared.services.work.order;

import org.eclipse.scout.rt.shared.TunnelToServer;
import org.eclipse.scout.rt.shared.services.lookup.ILookupService;

@TunnelToServer
public interface IWorkOrderAssignedToLookupService extends ILookupService<Long> {

}
