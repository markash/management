package threesixty.hr.core.party;

import java.util.Date;
import java.util.Objects;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Person extends Party {
	
	public static final String FIELD_FIRST_NAME 				= "firstName";
	public static final String FIELD_LAST_NAME 					= "lastName";
	public static final String FIELD_IDENTITY_NUMBER 			= "identityNumber";
	public static final String FIELD_DATE_OF_BIRTH 				= "dateOfBirth";
	
	@Column(name = "FIRST_NAME")
	private String firstName;
	
	@Column(name = "LAST_NAME")
	private String lastName;
	
	@Column(name = "BIRTH_DATE")
	private Date dateOfBirth;
	
	@Override
	@JsonbTransient
	public String getName() { return getFirstName() + " " + getLastName(); }
	
	@JsonbTransient
	public String getFirstName() { return firstName; }
	public void setFirstName(String firstName) { this.firstName = firstName; }
	
	@JsonbTransient
	public String getLastName() { return lastName; }
	public void setLastName(String lastName) { this.lastName = lastName; }
	
	public Date getDateOfBirth() { return dateOfBirth; }
	public void setDateOfBirth(Date dateOfBirth) { this.dateOfBirth = dateOfBirth; }

	@Override
    public boolean equals(
    		final Object o) {
        
		if (this == o) {
            return true;
        }
        if (!(o instanceof Person)) {
            return false;
        }

        Person other = (Person) o;

        return Objects.equals(getPartyId(), other.getPartyId());
	}
	
	@Override
    public int hashCode() {
        return getPartyId().hashCode();
    }
}
