package threesixty.hr.management.shared.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

public class LocalDateUtility {

	/**
	 * Converts a LocalDateTime to a Date instance using the default ZoneId
	 * @param date The local date to convert
	 * @return The date instance
	 */
	public static Date toDate(
			final LocalDateTime date) {
		
		return Optional.ofNullable(date)
				.map(d -> Date.from(d.atZone(ZoneId.systemDefault()).toInstant()))
				.orElse(null);
	}
	
	/**
	 * Converts a LocalDate to a Date instance using the default ZoneId
	 * @param date The local date to convert
	 * @return The date instance
	 */
	public static Date toDate(
			final LocalDate date) {
		
		return Optional.ofNullable(date)
				.map(d -> Date.from(d.atStartOfDay(ZoneId.systemDefault()).toInstant()))
				.orElse(null);
	}
	
	public static LocalDateTime toLocalDateTime(
			final Date date) {

		return Optional.ofNullable(date)
				.map(d -> LocalDateTime.ofInstant(d.toInstant(), ZoneId.systemDefault()))
				.orElse(null);
	}
	
	public static LocalDate toLocalDate(
			final Date date) {

		return Optional.ofNullable(date)
				.map(d -> toLocalDateTime(d).toLocalDate())
				.orElse(null);
	}
}
