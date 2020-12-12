package threesixty.hr.core.party;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import threesixty.hr.core.BaseEntity;
import threesixty.hr.core.EffectiveDateEntity;

@Entity
@Table(name = "Relationship")
public class PartyRelationship extends PanacheEntityBase implements BaseEntity, EffectiveDateEntity, Serializable {
	private static final long serialVersionUID = -5279303015062782630L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "RELATIONSHIP_ID")
	public Long relationshipId;
	
	@Column(name = "RELATIONSHIP_TYPE_ID")
	public RelationshipType type;
	
	@Column(name = "FROM_PARTY_ID")
	public Long fromPartyId;
	
    @Column(name = "TO_PARTY_ID")
	public Long toPartyId;
	
	@Column(name = "EFD")
	private LocalDateTime effectiveFrom;
	
	@Column(name = "ETD")
	private LocalDateTime effectiveTo;

	@Column(name = "PROCESSED")
	public LocalDateTime processed;
	
	public Long getFromPartyId() { return fromPartyId; }
	public void setFromPartyId(Long fromPartyId) { this.fromPartyId = fromPartyId; }
	
	public Long getToPartyId() { return toPartyId; }
	public void setToPartyId(Long toPartyId) { this.toPartyId = toPartyId; }
	
	@Override
	public LocalDateTime getEffectiveFrom() { return effectiveFrom; }
	@Override
	public void setEffectiveFrom(final LocalDateTime effectiveFrom) { this.effectiveFrom = effectiveFrom; }
	@Override
	public LocalDateTime getEffectiveTo() { return effectiveTo; }
	@Override
	public void setEffectiveTo(final LocalDateTime effectiveTo) { this.effectiveTo = effectiveTo; }
	
	@Override
	public LocalDateTime getProcessed() { return this.processed; }
	@Override
	public void setProcessed(final LocalDateTime processed) { this.processed = processed; }
	
	/**
	 * Whether the partyId is involved in the relationship
	 * @param partyId The partyId to check for involvement
	 * @return True if the partyId is involved else false
	 */
	public boolean isInvolved(
			final Long partyId) {
		
		return (this.toPartyId != null && this.toPartyId.equals(partyId)) || 
				(this.fromPartyId != null && this.fromPartyId.equals(partyId));
	}
	
	/**
	 * Whether the partyId is involved in the relationship
	 * @param partyId The partyId to check for involvement
	 * @param relationshipType The relationshipType
	 * @return True if the partyId is involved with the specified relationship type else false
	 */
	public boolean isInvolved(
			final Long partyId,
			final RelationshipType type) {
		
		return isInvolved(partyId) && this.type == type;
	}
	
	/*
	 * Validate that both parties involved in a relation is completed.
	 */
	public void validateRelationshipComplete() {

		if (this.fromPartyId == null || this.toPartyId == null) {
			
			//throw new PartyRelationshipIncompleteVetoException();
		}
	}
	
	/**
	 * Only relationships between two parties is allowed. A self relationship is not.
	 */
	public void validateRelationshipBetweenParties() {

		validateRelationshipComplete();
		
		if (this.fromPartyId == this.toPartyId) {
			
			//throw new PartyRelationshipWithSelfVetoException();
		}
	}
}
