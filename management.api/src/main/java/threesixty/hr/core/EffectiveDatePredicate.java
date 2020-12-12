package threesixty.hr.core;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.function.Predicate;

public class EffectiveDatePredicate implements Predicate<EffectiveDateEntity> {

	private final Validity validity;
	
	private final LocalDateTime logicalDate;
	
	public EffectiveDatePredicate(
			final LocalDateTime logicalDate,
			final Validity validity) {
		
		this.logicalDate = logicalDate;
		
		this.validity = validity;
	}
	
	@Override
	public boolean test(
			final EffectiveDateEntity entity) {
		
		LocalDateTime efd = Optional.ofNullable(entity.getEffectiveFrom()).orElse(LocalDateTime.MIN);
		LocalDateTime etd = Optional.ofNullable(entity.getEffectiveTo()).orElse(LocalDateTime.MIN);
		
		final Boolean valid = efd.compareTo(logicalDate) <= 0 && logicalDate.compareTo(etd) <= 0;
		
		return validity == Validity.VALID ? valid : !valid;
	}
}
