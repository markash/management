package threesixty.hr.application.model.party;

public class PartyNameDo {

	public String name;
	
	public PartyNameTypeDo type;
	
	public PartyNameDo withName(
			final String name) {
		
		this.name = name;
		return this;
	}
	
	public PartyNameDo withType(
			final PartyNameTypeDo type) {
		
		this.type = type;
		return this;
	}
}
