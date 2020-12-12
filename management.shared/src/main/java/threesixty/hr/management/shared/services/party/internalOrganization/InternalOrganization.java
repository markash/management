package threesixty.hr.management.shared.services.party.internalOrganization;

import java.util.Date;

import threesixty.hr.management.shared.services.DirectNamedEntity;
import threesixty.hr.management.shared.services.party.Party;

public class InternalOrganization extends Party implements DirectNamedEntity {
	private static final long serialVersionUID = 4512566825302994550L;
	
	public Date dateOfRegistration;
}
