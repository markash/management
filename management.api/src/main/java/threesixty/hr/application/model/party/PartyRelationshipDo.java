package threesixty.hr.application.model.party;

import java.time.LocalDateTime;

import threesixty.hr.core.party.RelationshipType;

public class PartyRelationshipDo {

	public Long id;
	
	public RelationshipType type;
	
	public PartyDo from;
	
	public PartyDo to;
	
	public LocalDateTime effectiveFrom;
	
	public LocalDateTime effectiveTo;
}
