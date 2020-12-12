package threesixty.hr.core.party;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import threesixty.hr.core.EffectiveDatePredicate;
import threesixty.hr.core.Validity;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Party extends PanacheEntityBase {

	public static final String FIELD_PARTY_ID 				= "id";
	public static final String FIELD_PARTY_TYPE_ID			= "typeId";
	public static final String FIELD_PROCESSED_DATE_TIME	= "processedDateTime";
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "PARTY_ID")
	private Long partyId;
	
	@Column(name = "TYPE_ID")
	private PartyType type;
	
	@OneToMany(mappedBy = "fromPartyId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY) 
	private Set<PartyRelationship> fromRelationships = new HashSet<>();
	
	@OneToMany(mappedBy = "toPartyId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY) 
	private Set<PartyRelationship> toRelationships = new HashSet<>();
	
	@OneToMany(mappedBy = "assignedToPartyId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY) 
	private Set<PartyIdentifier> indentifierValues = new HashSet<>();
	
	@OneToMany(mappedBy = "labelledByPartyId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER) 
	private Set<PartyName> names = new HashSet<>();
	
	@Column(name = "PROCESSED")
	public Date processedDateTime;
	
	public Long getPartyId() { return partyId; }
	public void setPartyId(Long partyId) { this.partyId = partyId; }
	
	public PartyType getType() { return type; }
	public void setType(PartyType type) { this.type = type; }
	
	/**
	 * Return the party names. The scoping of the name to a logical date is skipped.
	 * @return The list of party names for the logical date
	 */
	public List<PartyName> getNames() {
		
		return getNames((LocalDate) null);
	}
	
	/**
	 * Return the party names
	 * @param logicalDate The logical date for effective from and to date scoping
	 * @return The list of party names for the logical date
	 */
	public List<PartyName> getNames(
			final LocalDate logicalDate) {
		
		return Optional.ofNullable(this.names)
					.orElse(new HashSet<>())
					.stream()
					.filter(name -> name.isEffective(logicalDate))
					.collect(Collectors.toUnmodifiableList());
	}
	
	public String getName() {
		
		return PartyNamingStrategies.name(this);
	}
	
	/**
	 * Return the identifiers of a party.<br />
	 * 
	 * Identifies differ from names in that a {@link Party} manages that the identifier
	 * whereas a name could be given to an party by another or itself.<br /> 
	 * 
	 * Also identifiers uniquely identify a {@link Party} in some context, i.e. {@link PartyIdentifierType}
	 * which is managed by a {@link Party} and could be issued by the same or a different {@link Party}.
	 * 
	 * @return The list of party identifiers
	 */
	public List<PartyIdentifier> getIdentifiers() {
		
		return Optional.ofNullable(indentifierValues)
					.orElse(new HashSet<>())
					.stream().collect(Collectors.toUnmodifiableList());
	}

	/**
	 * Get the relationships of the person
	 * @return List of relationships
	 */
	public List<PartyRelationship> getRelationships() {
		
		Set<PartyRelationship> relationships = new HashSet<>(this.fromRelationships);
		relationships.addAll(this.toRelationships);
		
		return relationships.stream()
				.collect(Collectors.toUnmodifiableList());
	}
	
	/**
	 * Return whether the party has a relationship with another party of type.
	 * @param type The relationship type
	 * @param partyId The other party id
	 * @return True if this person has a relationship to the other party with the specified relationship type
	 */
	public boolean hasRelationshipType(
			final RelationshipType type,
			final Long partyId) {
	
		return this.fromRelationships.stream()
				.anyMatch(relationship -> relationship.isInvolved(partyId, type));
	}
	
	/**
	 * Get the relationships of the person that are of the specified relationship type
	 * @param relationshipType The type of relationship
	 * @return List of relationships of the specified type
	 */
	public List<PartyRelationship> getRelationships(
			final RelationshipType relationshipType) {
		
		final List<PartyRelationship> filteredRelationships =
				this.fromRelationships.stream()
					.filter(relationship -> relationship.type == relationshipType)
					.collect(Collectors.toList());
		
		return Collections.unmodifiableList(filteredRelationships);
	}
	
	/**
	 * Remove the inactive items from the object tree where the logical date
	 * is outside of the effective from and effective to dates.
	 * 
	 * @param logicalDate The logical date
	 */
	public void removeInactiveItems(
			final LocalDateTime logicalDate) {
		
		EffectiveDatePredicate predicate = 
				new EffectiveDatePredicate(logicalDate, Validity.INVALID);
		
		List<PartyRelationship> invalidRelationships =
				this.fromRelationships.stream()
					.filter(predicate)
					.collect(Collectors.toList());
		
		this.fromRelationships.removeAll(invalidRelationships);
		
		invalidRelationships = 
				this.toRelationships.stream()
					.filter(predicate)
					.collect(Collectors.toList());

		this.toRelationships.removeAll(invalidRelationships);
	}
	
	public void add(
			final List<PartyRelationship> partyRelationships) {
		
		partyRelationships.forEach(this::add);
	}
	
	public void add(final PartyRelationship partyRelationship) {
		
		partyRelationship.validateRelationshipComplete();
		
		partyRelationship.validateRelationshipBetweenParties();
		
		this.fromRelationships.add(partyRelationship);
	}

	/**
	 * Get the list of names that are invalid for this party
	 * @return The list of invalid part names
	 */
	@JsonbTransient
	public List<PartyName> getInvalidPartyNames() {
		
		List<PartyNameTypeAssignment> assignments = 
				PartyNameTypeAssignment.listActive(LocalDateTime.now());
		
		PartyNameAssigmentPredicate invalidAssignmentPredicate =
				new PartyNameAssigmentPredicate(type, assignments, Validity.INVALID);
		
		/* Passing null to an effectiveDateEntity returns all entities */
		return getNames((LocalDate) null)
				.stream()
				.filter(invalidAssignmentPredicate)
				.collect(Collectors.toList());
	}
	
	/**
	 * Whether the partyId matches this objects partyId
	 * @param partyId The partyId to match
	 * @return True if the partyId matches this object's partyId
	 */
	public boolean idEquals(final Long partyId) {
		
		return Objects.equals(this.partyId, partyId);
	}
	
	@Override
    public boolean equals(
    		final Object o) {
        
		if (this == o) {
            return true;
        }
        if (!(o instanceof Party)) {
            return false;
        }

        Party other = (Party) o;

        return Objects.equals(partyId, other.partyId);
    }

    @Override
    public int hashCode() {
        return partyId.hashCode();
    }
	
	@SuppressWarnings("java:S3252")
    public static List<Party> find(
			final List<Long> partyId) {
		
    	List<Long> identifiers = Optional.ofNullable(partyId).orElse(new ArrayList<>());
    	
		return Party.streamAll()
				.filter(party -> identifiers.contains(((Party) party).getPartyId()))
				.map(entity -> (Party) entity)
				.collect(Collectors.toList());
	}
    
    
    
    private static class PartyNameAssigmentPredicate implements Predicate<PartyName> {
		
    	private final Validity validity;
    	
    	private final List<PartyNameTypeAssignment> assignments;
		
		public PartyNameAssigmentPredicate(
				final PartyType type,
				final Collection<PartyNameTypeAssignment> assignments,
				final Validity validity) {
		
			this.validity = validity;
			
			this.assignments = 
					assignments.stream()
						.filter(assignment -> assignment.partyType == type)
						.collect(Collectors.toList());
		}

		@Override
		public boolean test(
				final PartyName partyName) {
			
			if (this.validity == Validity.VALID) {
				
				return assignments.stream()
					.anyMatch(assignment -> assignment.partyNameType.equals(partyName.type));
			}
			
			return assignments.stream()
					.noneMatch(assignment -> assignment.partyNameType.equals(partyName.type));
		}
	}
}
