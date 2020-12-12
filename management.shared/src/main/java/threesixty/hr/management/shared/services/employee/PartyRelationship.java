package threesixty.hr.management.shared.services.employee;

import java.math.BigInteger;
import java.util.Date;

public class PartyRelationship {

	public static final String FIELD_ID 					= "id";
	public static final String FIELD_TYPE_ID 				= "typeId";
	public static final String FIELD_FROM_ID 				= "fromId";
	public static final String FIELD_TO_ID 					= "toId";
	public static final String FIELD_EFFECTIVE_FROM 		= "effectiveFrom";
	public static final String FIELD_EFFECTIVE_TO 			= "effectiveTo";
	
	private BigInteger id;
	private RelationshipType type;
	private Party from;
	private Party to;
	private Date effectiveFrom;
	private Date effectiveTo;
	
	public PartyRelationship() {
		
		this.from = new Party();
		this.to = new Party();
	}
	
	protected PartyRelationship(
			final Party from, 
			final Party to) {
		
		this.from = from;
		this.to = to;
	}

	public void setId(BigInteger id) { this.id = id; }
	public BigInteger getId() { return id; }
	
	public void setFromId(BigInteger id) { this.getFrom().setId(id); }
	
	public void setToId(BigInteger id) { this.getTo().setId(id); }
	
	public Party getFrom() { return from; }

	public Party getTo() { return to; }
	
	public RelationshipType getType() { return type; }
	public void setType(RelationshipType type) { this.type = type; }
	
	public int getTypeId() { return getType().ordinal(); }
	public void setTypeId(int typeId) { this.setType(RelationshipType.valueOf(typeId)); }
	
	public Date getEffectiveFrom() { return effectiveFrom; }
	public void setEffectiveFrom(Date effectiveFrom) { this.effectiveFrom = effectiveFrom; }
	
	public Date getEffectiveTo() { return effectiveTo; }
	public void setEffectiveTo(Date effectiveTo) { this.effectiveTo = effectiveTo; }
	
	/**
	 * Whether the partyId is involved in the relationship
	 * @param partyId The partyId to check for involvement
	 * @return True if the partyId is involved else false
	 */
	public boolean isInvolved(final BigInteger partyId) {
		
		return (getTo() != null && getTo().getId().equals(partyId)) || 
				(getFrom() != null && getFrom().getId().equals(partyId));
	}
	
	/**
	 * Whether the partyId is involved in the relationship
	 * @param partyId The partyId to check for involvement
	 * @param relationshipType The relationshipType
	 * @return True if the partyId is involved with the specified relationship type else false
	 */
	public boolean isInvolved(
			final BigInteger partyId,
			final RelationshipType type) {
		
		return isInvolved(partyId) && this.getType() == type;
	}
	
	/*
	 * Validate that both parties involved in a relation is completed.
	 */
	public void validateRelationshipComplete() {

		if (getFrom() == null || getTo() == null) {
			
			throw new PartyRelationshipIncompleteVetoException();
		}
	}
	
	/**
	 * Only relationships between two parties is allowed. A self relationship is not.
	 */
	public void validateRelationshipBetweenParties() {

		validateRelationshipComplete();
		
		if (getFrom().getId() == getTo().getId()) {
			
			throw new PartyRelationshipWithSelfVetoException();
		}
	}
}
