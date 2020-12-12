package threesixty.hr.management.shared.services.party.person;

import org.eclipse.scout.rt.shared.services.lookup.ILookupService;
import org.eclipse.scout.rt.shared.services.lookup.LookupCall;

public class PersonLookupCall extends LookupCall<Long> {
	private static final long serialVersionUID = -8931483791399073359L;

	@Override
	protected Class<? extends ILookupService<Long>> getConfiguredService() {
		return IPersonLookupService.class;
	}
}
