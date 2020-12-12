package threesixty.hr.management.shared.services.employee;

import java.util.Arrays;

public enum PartyType {
	EXTERNAL_ORGANIZATION(
			1,		
			"External Organization"),
	PERSON(
			2, 		
			"Person"),
	
	INTERNAL_ORGANIZATION(
			3,		
			"Internal Organization")
	;
	
	private final int id;
	private final String name;
	
	private PartyType(
			final int id,
			final String name) {
		
		this.id = id;
		this.name = name;
	}
	
	public int getId() { return id; }
	
	public String getName() { return name; }
	
	/**
	 * Whether this party type is a member of the party types passed in
	 * @param partyTypes The list of party types to test for membership
	 * @return True if this party type is a member of the passed in party types
	 */
	public boolean either(
			final PartyType...partyTypes) {
		
		return Arrays.asList(partyTypes).contains(this);
	}
	
	/**
	 * Retrieve the party type based upon the id
	 * @param id The part type id
	 * @return The party type
	 */
	public static PartyType valueOf(int id) {
		
		return Arrays.stream(PartyType.values())
				.filter(type -> type.getId() == id)
				.findAny().orElse(null);
	}
}
