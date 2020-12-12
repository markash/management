package threesixty.hr.core.date;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.Optional;

import org.jboss.logging.Logger;

public class DateRange {
	private static final Logger LOG = Logger.getLogger(DateRange.class);

	private final LocalDateTime from;
	private final LocalDateTime to;
	
	public DateRange(
			final LocalDateTime from, 
			final LocalDateTime to) {
		
		this.from = from;
		this.to = to;
	}
	
	public LocalDateTime getFrom() { return from; }
	public LocalDateTime getTo() { return to; }
	
	/**
	 * Determines whether this date range contains the date time specified. The date time
	 * is considered to be contained if the dateTime >= from && dateTime <= to.
	 *  
	 * @param dateTime The date time to test for containment
	 * @return True if the dateTime is contained in the date range else false. A null date time returns false.
	 */
	public boolean containsInclusive(final LocalDateTime dateTime) {

		if (Objects.isNull(dateTime)) {
			return false;
		}

		final LocalDateTime begin = Optional.ofNullable(this.from).orElse(LocalDateTime.MIN); 
		final LocalDateTime end = Optional.ofNullable(this.to).orElse(LocalDateTime.MAX);

		return dateTime.compareTo(begin) >= 0 && dateTime.compareTo(end) <= 0;
	}

	/**
	 * Determines whether this date range contains the date time specified. The date time
	 * is considered to be contained if the dateTime > from && dateTime < to.
	 *  
	 * @param dateTime The date time to test for containment
	 * @return True if the dateTime is contained in the date range else false. A null date time returns false.
	 */
	public boolean containsExclusive(final LocalDateTime dateTime) {

		if (Objects.isNull(dateTime)) {
			return false;
		}

		final LocalDateTime begin = Optional.ofNullable(this.from).orElse(LocalDateTime.MIN); 
		final LocalDateTime end = Optional.ofNullable(this.to).orElse(LocalDateTime.MAX);

		final boolean result = dateTime.compareTo(begin) > 0 && dateTime.compareTo(end) < 0;

		if (LOG.isDebugEnabled()) {

			final String message = 
				String.format(
					"%s IN %s is %s", 
					dateTime.toString(),
					this.toString(),
					result);

			LOG.debug(message);
		}

		return result;
	}

	/**
	 * Determines whether the dateRange passed has a from or to date that is contained by the current date range.
	 * 
	 * 
	 * @param dateRange The date range to test for containment
	 * @return True if the dateRange has a from or to date that is contained in the current date range
	 */
	public boolean containsExclusive(final DateRange dateRange) {

		if (Objects.isNull(dateRange) || 
			(Objects.isNull(dateRange.getFrom()) && Objects.isNull(dateRange.getTo()))) {
			return false;
		}

		final LocalDateTime begin = Optional.ofNullable(dateRange.getFrom()).orElse(LocalDateTime.MIN); 
		final LocalDateTime end = Optional.ofNullable(dateRange.getTo()).orElse(LocalDateTime.MAX);

		/* If both range have the same from or to date then the ranges are contained */
		if (begin.isEqual(this.from) || end.isEqual(this.to)) {
			return true;
		}

		return containsExclusive(begin) || containsExclusive(end);
	}

	/**
	 * Determines whether either date range contains the other date range
	 * @param dateRange The dateRange to test whether it overlaps thsi date range or that this date range overlaps it
	 * @return True is either this or the passed date range overlap each other
	 */
	public boolean overlapsExclusive(final DateRange dateRange) {

		final boolean result = this.containsExclusive(dateRange) || dateRange.containsExclusive(this);

		if (LOG.isDebugEnabled()) {
			
			LOG.debug(
				String.format(
					"%s overlaps %s is %s",
					dateRange, 
					this, 
					result));
		}

		return result;
	} 

	public String toString() {

		return String.format(
			"(%s -> %s)", 
			Optional.ofNullable(getFrom()).map(Object::toString).orElse("NULL"),
			Optional.ofNullable(getTo()).map(Object::toString).orElse("NULL"));
	}

	public static class Builder {
		
		private LocalDateTime from;
		private LocalDateTime to;
		
		private Builder(
				final LocalDateTime from) {
			
			this.from = from;
		}
		
		public static Builder from(final LocalDateTime from) {
			
			return new Builder(from);
		}
		
		public static Builder fromStartOfDay(
				final LocalDate from) {
			
			return new Builder(from.atStartOfDay());
		}

		public static Builder fromStartOfWeek(
				final LocalDate from) {
			
			final int days = Math.abs(DayOfWeek.MONDAY.getValue() - from.getDayOfWeek().getValue());
			
			return new Builder(from.minusDays(days).atStartOfDay());
		}
		
		public static Builder fromStartOfMonth(
				final String from) {
			
			return fromStartOfMonth(LocalDate.parse(from));
		}

		public static Builder fromStartOfMonth(
				final LocalDate from) {
			
			return new Builder(from.withDayOfMonth(1).atStartOfDay());
		}
		
		public Builder to(final LocalDateTime to) {
			
			this.to = to;
			return this;
		}
		
		public Builder toSecondBeforeMidnight(
				final LocalDate to) {
			
			this.to = to.atTime(23, 59, 59);
			return this;
		}

		public Builder toEndOfWeek(
				final LocalDate to) {
			
			final int days = DayOfWeek.SUNDAY.getValue() - from.getDayOfWeek().getValue();
			
			this.to = to.plusDays(days).atTime(23, 59, 59);
			return this;
		}
		
		public Builder toEndOfMonth(
				final String to) {
			
			return toEndOfMonth(LocalDate.parse(to));
		}

		public Builder toEndOfMonth(
				final LocalDate to) {
			
			this.to = to.plus(1, ChronoUnit.MONTHS).withDayOfMonth(1).minusDays(1).atTime(23, 59, 59);
			return this;
		}
		
		public DateRange build() {
			
			return new DateRange(from, to);
		}
	}
}
