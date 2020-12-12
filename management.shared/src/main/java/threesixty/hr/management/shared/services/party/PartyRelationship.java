package threesixty.hr.management.shared.services.party;

import java.io.Serializable;
import java.util.Date;

public class PartyRelationship implements Serializable {
	private static final long serialVersionUID = -1365008864037659928L;

	public Long id;
	public RelationshipType type;	
	public Party from;
	public Party to;
	public Date effectiveFrom;
	public Date effectiveTo;
}
