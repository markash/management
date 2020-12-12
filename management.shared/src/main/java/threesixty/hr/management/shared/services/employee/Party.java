package threesixty.hr.management.shared.services.employee;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

public class Party implements Serializable {
	private static final long serialVersionUID = 79088468757271225L;

	public static final String FIELD_PARTY_ID 				= "id";
	public static final String FIELD_PARTY_TYPE_ID			= "typeId";
	public static final String FIELD_PROCESSED_DATE_TIME	= "processedDateTime";
	
	private BigInteger id;
	private PartyType type;
	private Date processedDateTime;
	
	public BigInteger getId() { return id; }
	public void setId(BigInteger id) { this.id = id; }
	
	public PartyType getType() { return type; }
	public void setType(PartyType type) { this.type = type; }
	
	public int getTypeId() { return getType().ordinal(); }
	public void setTypeId(int typeId) { this.setType(PartyType.valueOf(typeId)); }
	
	public Date getProcessedDateTime() { return processedDateTime; }
	public void setProcessedDateTime(Date processedDateTime) { this.processedDateTime = processedDateTime; }
}
