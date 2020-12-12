package threesixty.hr.management.server.services.party;

import java.io.Serializable;

public class PartyNameType implements Serializable {
	private static final long serialVersionUID = 3604422453675044027L;

	private PartyNameTypes name;
	private String description;
	
	public PartyNameTypes getName() { return this.name; }
	public void setName(final PartyNameTypes name) { this.name = name; }
	
	public String getDescription() { return this.description; }
	public void setDescription(final String description) { this.description = description; }
}
