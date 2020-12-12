package threesixty.hr.management.shared.services.employee;

import org.eclipse.scout.rt.shared.TunnelToServer;
import org.eclipse.scout.rt.shared.services.lookup.ILookupService;

@TunnelToServer
public interface IEmployeeLookupService extends ILookupService<Long> {

}
