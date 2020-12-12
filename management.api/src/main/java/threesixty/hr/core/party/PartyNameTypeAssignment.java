package threesixty.hr.core.party;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import threesixty.hr.core.EffectiveDateEntity;
import threesixty.hr.core.EffectiveDatePredicate;
import threesixty.hr.core.Validity;

@Entity
@Table(name = "PARTY_NAME_TYPE_ASSIGNMENT")
public class PartyNameTypeAssignment extends PanacheEntityBase implements EffectiveDateEntity, Serializable {
	private static final long serialVersionUID = -5926363532015606491L;

	@Id
	@ManyToOne
	@JoinColumn(name = "PARTY_NAME_TYPE")
	public PartyNameType partyNameType;
	
	@Id
	@Column(name = "PARTY_TYPE_ID")
	public PartyType partyType;
	
	@Column(name = "COMMENT")
	public String comment;
	
	@Id
	@Column(name = "EFD")
	private LocalDateTime effectiveFrom;
	
	@Column(name = "ETD")
	private LocalDateTime effectiveTo;
	
	@Override
	public LocalDateTime getEffectiveFrom() { return effectiveFrom; }
	@Override
	public void setEffectiveFrom(final LocalDateTime effectiveFrom) { this.effectiveFrom = effectiveFrom; }
	@Override
	public LocalDateTime getEffectiveTo() { return effectiveTo; }
	@Override
	public void setEffectiveTo(final LocalDateTime effectiveTo) { this.effectiveTo = effectiveTo; }
	
	
	public static List<PartyNameTypeAssignment> listActive(
			final LocalDateTime logicalDate) {
		
		final EffectiveDatePredicate effectiveDatePredicate = 
				new EffectiveDatePredicate(logicalDate, Validity.VALID);
		
		return listAll().stream()
					.map(entity -> (PartyNameTypeAssignment) entity)
					.filter(effectiveDatePredicate)
					.collect(Collectors.toList());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(effectiveFrom, partyNameType, partyType);
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (this == obj) { return true; }
		
		if (obj == null) { return false; }
		
		if (getClass() != obj.getClass()) { return false; }
		
		PartyNameTypeAssignment other = (PartyNameTypeAssignment) obj;
		
		return 	   Objects.equals(effectiveFrom, other.effectiveFrom) 
				&& Objects.equals(partyNameType, other.partyNameType)
				&& partyType == other.partyType;
	}
	
	
}
