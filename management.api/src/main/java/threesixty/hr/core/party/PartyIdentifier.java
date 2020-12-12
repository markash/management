package threesixty.hr.core.party;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name = "PARTY_IDENTIFIER")
public class PartyIdentifier extends PanacheEntityBase implements Serializable {
	private static final long serialVersionUID = 3984843560102260356L;

	@Id @Column(name = "ASSIGNEDTO_PARTY_ID")
	public Long assignedToPartyId;
	
	@Id @Column(name = "IDENTIFIER")
	public String identifier;
	
	@Id @Column(name = "VALUE")
	public String value;
	
	@Column(name = "ISSUEDBY_PARTY_ID")
	public Long issuedByPartyId;
	
	@Column(name = "EFD")
	public Date effectiveFrom;
	
	@Column(name = "ETD")
	public Date effectiveTo;

	@Override
	public int hashCode() {
		return Objects.hash(assignedToPartyId, identifier, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) { return true; }
		
		if (obj == null) { return false; }
		
		if (getClass() != obj.getClass()) { return false; }
		
		PartyIdentifier other = (PartyIdentifier) obj;
		
		return     Objects.equals(assignedToPartyId, other.assignedToPartyId)
				&& Objects.equals(identifier, other.identifier) 
				&& Objects.equals(value, other.value);
	}
	
	
}
