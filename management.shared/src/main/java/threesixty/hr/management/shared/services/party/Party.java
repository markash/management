package threesixty.hr.management.shared.services.party;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import threesixty.hr.management.shared.services.DirectNamedEntity;
import threesixty.hr.management.shared.services.employee.PartyType;

public class Party implements DirectNamedEntity, Serializable {
	private static final long serialVersionUID = -4243062257663644521L;

	public Long id;
	public PartyType type;
	public String name;
	
	public List<PartyRelationship> relationships = new ArrayList<>();
	
	//private List<PartyIdentifier> identifiers = new ArrayList<>();
	//private Set<PartyName> names = new HashSet<>();
		
	@Override
	public String getName() { return name; }

	@Override
	public void setName(final String name) { this.name = name; }
	
	public List<PartyRelationship> getRelationships(
			final RelationshipType relationshipType) { 
		
		return relationships.stream()
				.filter(relationship -> relationship.type == relationshipType)
				.collect(Collectors.toUnmodifiableList());
	}
	
	public Party getEmployer() {
		
		if (type == PartyType.PERSON) {
			
			return getRelationships(RelationshipType.EMPLOYMENT).stream()
					.map(relationship -> relationship.from)
					.findFirst()
					.orElse(null);
		}
		
		return null;
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

        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return 31;
    }
}