package threesixty.hr.management.shared.services.employee;

import java.util.Date;

public class Organization extends Party {
	private static final long serialVersionUID = 4512566825302994550L;
	
	public static final String FIELD_NAME 						= "name";
	public static final String FIELD_REGISTRATION_NUMBER 		= "registrationNumber";
	public static final String FIELD_DATE_OF_REGISTRATION 		= "dateOfRegistration";
	public static final String FIELD_PROCESSED_DATE_TIME 		= "processedDateTime";
	
	private String name;
	private String registrationNumber;
	private Date dateOfRegistration;
	private Date processedDateTime;
	
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	public String getRegistrationNumber() { return registrationNumber; }
	public void setRegistrationNumber(String registrationNumber) { this.registrationNumber = registrationNumber; }
	
	public Date getDateOfRegistration() { return dateOfRegistration; }
	public void setDateOfRegistration(Date dateOfRegistration) { this.dateOfRegistration = dateOfRegistration; }
	
	public Date getProcessedDateTime() { return processedDateTime; }
	public void setProcessedDateTime(Date processedDateTime) { this.processedDateTime = processedDateTime; }
}
