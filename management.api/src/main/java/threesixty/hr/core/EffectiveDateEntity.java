package threesixty.hr.core;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

/**
 * An entity that has an effective date to and from
 * @author Mark Ashworth
 */
public interface EffectiveDateEntity {

	LocalDateTime getEffectiveFrom();
	void setEffectiveFrom(final LocalDateTime effectiveFrom);
	
	LocalDateTime getEffectiveTo();
	void setEffectiveTo(final LocalDateTime effectiveTo);
	
	/**
	 * Determines if the entity is effective for the date, i.e. is between the 
	 * effective from and to dates.<br />
	 * 
	 * If the logical date is null then the entity is deemed effective, i.e.
	 * that the entity should not be scoped to an logical date. This is the same as 
	 * saying `get all records regardless of the effective date`<br />
	 * 
	 * If the effective from and to dates are null then the entity is
	 * effective or logically deleted.<br />
	 * 
	 * @param logicalDate The logical date to determine if the entity is effective
	 */
	default boolean isEffective(
			final LocalDate logicalDate) {
		
		return isEffective(logicalDate != null ? logicalDate.atStartOfDay() : null);
	}
	
	/**
	 * Determines if the entity is effective for the date, i.e. is between the 
	 * effective from and to dates.<br />
	 * 
	 * If the logical date is null then the entity is deemed effective, i.e.
	 * that the entity should not be scoped to an logical date. This is the same as 
	 * saying `get all records regardless of the effective date`<br />
	 * 
	 * If the effective from and to dates are null then the entity is
	 * effective or logically deleted.<br />
	 * 
	 * @param logicalDate The logical date to determine if the entity is effective
	 */
	default boolean isEffective(
			final LocalDateTime logicalDateTime) {
		
		if (getEffectiveFrom() == null && getEffectiveTo() == null) {
			return false;
		}
		
		if (logicalDateTime == null) {
			return true;
		}
		
		ZonedDateTime date = 
				ZonedDateTime.of(logicalDateTime, ZoneId.systemDefault());
		
		ZonedDateTime efd = 
				Optional.ofNullable(getEffectiveFrom())
					.map(d -> ZonedDateTime.of(d, ZoneId.systemDefault()))
					.orElse(ZonedDateTime.of(LocalDate.EPOCH.atStartOfDay(), ZoneId.systemDefault()));
		
		ZonedDateTime etd = 
				Optional.ofNullable(getEffectiveTo())
					.map(d -> ZonedDateTime.of(d, ZoneId.systemDefault()))
					.orElse(ZonedDateTime.of(LocalDate.MAX.atTime(23, 59, 59), ZoneId.systemDefault()));
		
		return (date.isEqual(efd) || date.isAfter(efd)) 
			&& (date.isEqual(efd) || date.isBefore(etd));
	}
}
