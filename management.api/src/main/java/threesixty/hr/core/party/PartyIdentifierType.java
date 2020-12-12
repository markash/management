package threesixty.hr.core.party;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name = "PARTY_IDENTIFIER_TYPE")
public class PartyIdentifierType extends PanacheEntityBase implements Serializable {
	private static final long serialVersionUID = -5376379528453562717L;
	
	@Id @Column(name = "NAME")
	public String name;
	
	@Id @Column(name = "DESCRIPTION")
	public String description;
	
	@Id @Column(name = "MANAGEDBY_PARTY_ID")
	public Long managedByPartyId;
	
	@Override
	public int hashCode() {
		return Objects.hash(managedByPartyId, name);
	}

	@Override
	public boolean equals(Object obj) {
		
		if (this == obj) { return true; }
		if (obj == null) { return false; }
		
		if (getClass() != obj.getClass()) { return false; }
		
		PartyIdentifierType other = (PartyIdentifierType) obj;
		return Objects.equals(managedByPartyId, other.managedByPartyId) && Objects.equals(name, other.name);
	}
}
