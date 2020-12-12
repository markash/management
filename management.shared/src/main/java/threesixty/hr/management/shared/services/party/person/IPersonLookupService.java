package threesixty.hr.management.shared.services.party.person;

import org.eclipse.scout.rt.shared.TunnelToServer;
import org.eclipse.scout.rt.shared.services.lookup.ILookupService;

@TunnelToServer
public interface IPersonLookupService extends ILookupService<Long> {

}
