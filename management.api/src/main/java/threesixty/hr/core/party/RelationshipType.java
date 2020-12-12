package threesixty.hr.core.party;

import java.util.Arrays;

public enum RelationshipType {
	UNKNOWN(0, "Unknown"),
	EMPLOYMENT(1, 		"Employment"),
	TEAM_MEMBER(2, 		"Team Member"),
	MANAGER(3,			"Manager");
	
	private final int id;
	private final String name;
	
	private RelationshipType(
			final int id,
			final String name) {
		
		this.id = id;
		this.name = name;
	}
	
	public int getId() { return id; }
	
	public String getName() { return name; }
	
	/**
	 * Retrieve the relationship type based upon the id
	 * @param id The relationship type id
	 * @return The relationship type
	 */
	public static RelationshipType valueOf(int id) {
		
		return Arrays.stream(RelationshipType.values())
				.filter(type -> type.getId() == id)
				.findAny().orElse(null);
	}
}
