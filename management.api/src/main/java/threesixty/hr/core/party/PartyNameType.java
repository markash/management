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
@Table(name = "PARTY_NAME_TYPE")
public class PartyNameType extends PanacheEntityBase implements Serializable {
	private static final long serialVersionUID = 3425098196077694098L;

	@Id @Column(name = "NAME")
	public String name;
	
	@Column(name = "DESCRIPTION")
	public String description;
	
	@Column(name = "PROCESSED")
	public Date processedDateTime;
	
	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(
			final Object obj) {
		
		if (this == obj) { return true; }
		
		if (obj == null) { return false; }
		
		if (getClass() != obj.getClass()) {return false; }
		
		PartyNameType other = (PartyNameType) obj;
		return Objects.equals(name, other.name);
	}
}
