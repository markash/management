package threesixty.hr.application.model.party;

import java.util.List;
import java.util.Optional;

import javax.json.bind.annotation.JsonbTransient;

import threesixty.hr.core.party.PartyType;

public class PartyDo {

	public Long id; 
	
	public PartyType type;
	
	@JsonbTransient
	public List<PartyNameDo> names;
	
	public List<PartyRelationshipDo> relationships;
	
	protected PartyNameDo getName(
			final PartyNameTypeDo type) {
		
		if (type == null) { return null; }
		
		return this.names.stream()
				.filter(name -> Optional.ofNullable(name).filter(n -> n.type == type).isPresent())
				.findFirst()
				.orElse(null);
	}
	
	protected String getNameAsString(
			final PartyNameTypeDo type) {
		
		return Optional.ofNullable(getName(type))
				.map(name -> name.name)
				.orElse(null);
	}
}
