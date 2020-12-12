package threesixty.hr.core.party;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import threesixty.hr.core.DirectNamedEntity;
import threesixty.hr.core.EffectiveDateEntity;

@Entity
@Table(name = "PARTY_NAME")
public class PartyName extends PanacheEntityBase implements DirectNamedEntity, EffectiveDateEntity, Serializable {
	private static final long serialVersionUID = -2911436536305380749L;

	@Id @Column(name = "LABELLED_PARTY_ID")
	public Long labelledByPartyId;
	
	@Id
	@Column(name = "NAME")
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "PARTY_NAME_TYPE")
	public PartyNameType type;
	
	@Column(name = "ISSUEDBY_PARTY_ID")
	public Long issuedByPartyId;
	
	public String comment;
	
	@Column(name = "EFD")
	private LocalDateTime effectiveFrom;
	
	@Column(name = "ETD")
	private LocalDateTime effectiveTo;
	
	@Column(name = "PROCESSED")
	private LocalDateTime processed;
	
	@Override
	public String getName() { return this.name; }
	@Override
	public void setName(final String name) { this.name = name; }
	
	
	@Override
	public LocalDateTime getEffectiveFrom() { return this.effectiveFrom; }
	@Override
	public void setEffectiveFrom(final LocalDateTime effectiveFrom) { this.effectiveFrom = effectiveFrom; }
	
	@Override
	public LocalDateTime getEffectiveTo() { return this.effectiveTo; }
	@Override
	public void setEffectiveTo(final LocalDateTime effectiveTo) { this.effectiveTo = effectiveTo; }
	
	@Override
	public LocalDateTime getProcessed() { return processed; }
	@Override
	public void setProcessed(LocalDateTime processed) { this.processed = processed; }
	
	@Override
	public int hashCode() {
		return Objects.hash(labelledByPartyId, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) { return true; }
		
		if (obj == null) { return false; }
		
		if (getClass() != obj.getClass()) { return false; }
		
		PartyName other = (PartyName) obj;
		
		return     Objects.equals(labelledByPartyId, other.labelledByPartyId) 
				&& Objects.equals(name, other.name);
	}
}
