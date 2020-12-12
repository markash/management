package threesixty.hr.management.server.services.party;

import java.util.Date;

public class PartyName {

	private Long labelledByPartyId;
	private Long issuedByPartyId;
	private PartyNameType type;
	private String name;
	private String comment;
	private Date effectiveFrom;
	private Date effectiveTo;
	
	public Long getLabelledByPartyId() { return this.labelledByPartyId; }
	public void setLabelledByPartyId(final Long id) { this.labelledByPartyId = id; }
	
	public Long getIssuedByPartyId() { return issuedByPartyId; }
	public void setIssuedByPartyId(final Long id) { this.issuedByPartyId = id; }
	
	public PartyNameType getType() { return this.type; }
	public void setType(final PartyNameType type) { this.type = type; }
	
	public String getName() { return this.name; }
	public void setName(final String name) { this.name = name; }
	
	public String getComment() { return this.comment; }
	public void setComment(final String comment) { this.comment = comment; }
	
	public Date getEffectiveFrom() { return this.effectiveFrom; }
	public void setEffectiveFrom(final Date date) { this.effectiveFrom = date; }
	
	public Date getEffectiveTo() { return this.effectiveTo; }
	public void setEffectiveTo(final Date date) { this.effectiveTo = date; }
}
