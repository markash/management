package threesixty.hr.management.server.services.party;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class PartyIdentifier implements Serializable {
	private static final long serialVersionUID = 3984843560102260356L;

	public Long assignedToPartyId;
	public String identifier;
	public String value;
	public Long issuedByPartyId;
	public Date effectiveFrom;
	public Date effectiveTo;

	@Override
	public int hashCode() {
		return Objects.hash(assignedToPartyId, identifier, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) { return true; }
		
		if (obj == null) { return false; }
		
		if (getClass() != obj.getClass()) { return false; }
		
		PartyIdentifier other = (PartyIdentifier) obj;
		
		return     Objects.equals(assignedToPartyId, other.assignedToPartyId)
				&& Objects.equals(identifier, other.identifier) 
				&& Objects.equals(value, other.value);
	}
}
