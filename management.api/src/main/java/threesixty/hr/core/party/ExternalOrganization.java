package threesixty.hr.core.party;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "EXTERNAL_ORGANIZATION")
public class ExternalOrganization extends Party {
	
	public static final String FIELD_NAME 						= "name";
	public static final String FIELD_DATE_OF_REGISTRATION 		= "dateOfRegistration";
	public static final String FIELD_PROCESSED_DATE_TIME 		= "processedDateTime";
	
	@Column(name = "NAME")
	private String name;
		
	@Column(name = "REGISTRATION_DATE")
	private Date dateOfRegistration;
	
	@Column(name = "PROCESSED")
	private Date processedDateTime;
	
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public Date getDateOfRegistration() { return dateOfRegistration; }
	public void setDateOfRegistration(Date dateOfRegistration) { this.dateOfRegistration = dateOfRegistration; }
	
	public Date getProcessedDateTime() { return processedDateTime; }
	public void setProcessedDateTime(Date processedDateTime) { this.processedDateTime = processedDateTime; }
}
