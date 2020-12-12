package threesixty.hr.management.shared.services.party.internalOrganization;

import java.util.function.Function;

import org.eclipse.scout.rt.shared.services.lookup.LookupRow;

public class InternalOrganizationToLookupRow implements Function<InternalOrganization, LookupRow<Long>> {

	@Override
	public LookupRow<Long> apply(
			final InternalOrganization organization) {
		
		return new LookupRow<Long>(
				mapToCells(organization), 
				Long.class);
	}

	private Object[] mapToCells(
			final InternalOrganization organization) {
		
		return new Object[] {
				organization.id						/* key		*/,
				organization.name					/* text		*/,
				null 								/* iconId	*/,
				organization.name					/* tool tip	*/
		};
	}
}
