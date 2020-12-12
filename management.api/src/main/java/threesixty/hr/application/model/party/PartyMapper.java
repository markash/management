package threesixty.hr.application.model.party;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import threesixty.hr.core.party.InternalOrganization;
import threesixty.hr.core.party.Party;
import threesixty.hr.core.party.PartyName;
import threesixty.hr.core.party.PartyNameType;
import threesixty.hr.core.party.PartyRelationship;
import threesixty.hr.core.party.Person;

@Mapper(config = PartyMappingConfig.class)
public abstract class PartyMapper {

	@Inject
    EntityManager em;
	
	/**
	 * Maps a person entity model into a data object.
	 * 
	 * The logical date is to scope the data returned for entity values
	 * that have an effective from and to date.
	 * 
	 * @param person The person entity
	 * @param logicalDate The logical date to apply when determining values effective from and to date 
	 * @return The person data object
	 */
	@Mapping(target = "id", source = "partyId")
	@Mapping(target = "type", source = "type")
    public abstract PersonDo toPerson(Person person, @Context LocalDate logicalDate);

	@Mapping(target = "id", source = "partyId")
	@Mapping(target = "type", source = "type")
    public abstract InternalOrganizationDo toInternalOrganization(InternalOrganization organization, @Context LocalDate logicalDate);
	
	/**
	 * Maps the party names and only includes names that effective at the given logical date.
	 * 
	 * @param partyNames The party names to map to data objects
	 * @param logicalDate The logical date to constrain the party names based upon effective date
	 * @return The list of party name data objects
	 */
	public List<PartyNameDo> toPartyNames(List<PartyName> partyNames, @Context LocalDate logicalDate) {
		
		if (partyNames == null) { return null; }
		
		return partyNames.stream()
				.filter(name -> name.isEffective(logicalDate))
				.map(this::toPartyName)
				.collect(Collectors.toList());
	}
	
	/**
	 * Maps the party relationships and only includes relationships that are effective at the given logical date.
	 * 
	 * @param partyRelationships The party relationships to map to data objects
	 * @param logicalDate The logical date to constrain the party relationships based upon effective date
	 * @return The list of party relationship data objects
	 */
	public List<PartyRelationshipDo> toPartyRelationships(List<PartyRelationship> partyRelationships, @Context LocalDate logicalDate) {
		
		if (partyRelationships == null) { return null; }
		
		return partyRelationships.stream()
				.filter(relationship -> relationship.isEffective(logicalDate))
				.map(relationship -> toPartyRelationship(relationship, logicalDate))
				.collect(Collectors.toList());
	}
	
    @Mapping(target = "type", source = "type.name")
    @Mapping(target	="withName", ignore = true)
    @Mapping(target = "withType", ignore = true)
    public abstract PartyNameDo toPartyName(PartyName partyName);
    
    @Mapping(target = "id", source = "relationshipId")
    @Mapping(target = "from.id", source="fromPartyId")
    @Mapping(target = "to.id", source="toPartyId")
    @Mapping(target = "effectiveFrom", source = "effectiveFrom", dateFormat = "yyyy-MM-dd'T'hh:mm:ss")
    public abstract PartyRelationshipDo toPartyRelationship(PartyRelationship relationship, @Context LocalDate logicalDate);
    
    public abstract ExternalOrganizationDo toPartyToExternalOrganization(PartyDo party);
    public abstract InternalOrganizationDo toPartyToInternalOrganization(PartyDo party);
    public abstract PersonDo toPartyToPerson(PartyDo party);
    
    /**
     * Map a party name type to a string
     * @param partyNameType The party name type
     * @return The name of the party name type
     */
    public String map(
    		final PartyNameType partyNameType) {
        
    	return partyNameType.name;
    }
    
    /**
     * Ensure that the date conversion takes the current time zone into account
     * @param date The date
     * @return The local date and time
     */
    public LocalDateTime map(final Date date) {
    	
    	if (date == null) { return null; }
    	
    	return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }
    
    /**
     * Ensure that the person is scoped correctly to the logical date
     * by removing any entities that are not effective.
     * 
     * @param person
     * @param result
     * @param logicalDate
     */
    @AfterMapping
    protected void resolve(
    		final Person person,
    		final @MappingTarget PersonDo result,
    		final @Context LocalDate logicalDate) {
    	
    }
    
    @AfterMapping
    protected void resolve(
    		final PartyRelationship partyRelationship, 
    		final @MappingTarget PartyRelationshipDo result,
    		final @Context LocalDate logicalDate) {
    
    	Party fromParty = Party.findById(partyRelationship.fromPartyId);

    	Party toParty = Party.findById(partyRelationship.toPartyId);
    	
    	result.from.type = fromParty.getType();
    	result.from.names = toPartyNames(fromParty.getNames(logicalDate));
    	
    	result.to.type = toParty.getType();
    	result.to.names = toPartyNames(toParty.getNames(logicalDate));
    	
    	result.from = toPartySubType(result.from);
    	
    	result.to = toPartySubType(result.to);
    }
    
    private List<PartyNameDo> toPartyNames(
    		final List<PartyName> names) {
    	
    	if (names == null || names.isEmpty()) {
    		return new ArrayList<>();
    	}
    	
    	return names.stream()
    			.map(name -> toPartyName(name))
				.collect(Collectors.toList());
    }
    
    private PartyDo toPartySubType(
    		final PartyDo party) {
    	
    	switch (party.type) {
	    	case EXTERNAL_ORGANIZATION:
	    		return toPartyToExternalOrganization(party);
	    	case INTERNAL_ORGANIZATION:
	        	return toPartyToInternalOrganization(party);
	    	case PERSON:
	    		return toPartyToPerson(party);
	    	default:
	    		return null;
    	}
    }
}