package threesixty.hr.management.shared.services.employee;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.scout.rt.platform.util.StringUtility;

import threesixty.hr.management.shared.services.DirectNamedEntity;

public class Person extends Party implements DirectNamedEntity {
	private static final long serialVersionUID = 3862009051985159168L;

	public static final String FIELD_FIRST_NAME 				= "firstName";
	public static final String FIELD_LAST_NAME 					= "lastName";
	public static final String FIELD_IDENTITY_NUMBER 			= "identityNumber";
	public static final String FIELD_DATE_OF_BIRTH 				= "dateOfBirth";
	
	private String firstName;
	private String lastName;
	private String identityNumber;
	private Date dateOfBirth;
	private List<PartyRelationship> relationships = new ArrayList<>();
	
	@Override
	public String getName() { return getFirstName() + " " + getLastName(); }
	
	@Override
	public void setName(
			final String name) {
		
		if (StringUtility.isNullOrEmpty(name)) {
			
			setFirstName(null);
			setLastName(null);
			
		} else {
			
			String[] parts = name.split(" ");
			
			final String lastName =
					Arrays.stream(parts)
						.reduce((f, s) -> s)
						.orElse(null);
			
			final String firstName =
					name.replace(lastName, "").trim();
						
			setLastName(lastName);
			setFirstName(firstName);
		}
	}
	
	public String getFirstName() { return firstName; }
	public void setFirstName(String firstName) { this.firstName = firstName; }
	
	public String getLastName() { return lastName; }
	public void setLastName(String lastName) { this.lastName = lastName; }
	
	public String getIdentityNumber() { return identityNumber; }
	public void setIdentityNumber(String identityNumber) { this.identityNumber = identityNumber; }
	
	public Date getDateOfBirth() { return dateOfBirth; }
	public void setDateOfBirth(Date dateOfBirth) { this.dateOfBirth = dateOfBirth; }
	
	/**
	 * Return whether the party has a relationship with another party of type.
	 * @param type The relationship type
	 * @param partyId The other party id
	 * @return True if this person has a relationship to the other party with the specified relationship type
	 */
	public boolean hasRelationshipType(
			final RelationshipType type,
			final BigInteger partyId) {
	
		return this.relationships.stream()
				.anyMatch(relationship -> relationship.isInvolved(partyId, type));
	}
	
	/**
	 * Get the relationships of the person
	 * @return List of relationships
	 */
	public List<PartyRelationship> getRelationships() {
		
		return Collections.unmodifiableList(this.relationships);
	}
	
	/**
	 * Get the relationships of the person that are of the specified relationship type
	 * @param relationshipType The type of relationship
	 * @return List of relationships of the specified type
	 */
	public List<PartyRelationship> getRelationships(
			final RelationshipType relationshipType) {
		
		final List<PartyRelationship> filteredRelationships =
				this.relationships.stream()
					.filter(relationship -> relationship.getType() == relationshipType)
					.collect(Collectors.toList());
		
		return Collections.unmodifiableList(filteredRelationships);
	}
	
	public void add(final List<PartyRelationship> partyRelationships) {
		
		partyRelationships.forEach(this::add);
	}
	
	public void add(final PartyRelationship partyRelationship) {
		
		partyRelationship.validateRelationshipComplete();
		
		partyRelationship.validateRelationshipBetweenParties();
		
		this.relationships.add(partyRelationship);
	}
}
