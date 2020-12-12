package threesixty.hr.core.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateRangeUtility {

	/**
	 * The range for the number of hours starting at the from date time
	 * 
	 * The ISO_DATE_TIME format is used for the parsing of the date.
	 * 
	 * @param from The date and time which the range should start
	 * @param hours The number of hours to mark the end of the range
	 * @return The date time range
	 */
	public static DateRange forHour(
		final String from, 
		final Long hours) {
	
		return forHour(
			from,
			DateTimeFormatter.ISO_DATE_TIME,
			hours);
	}

	/**
	 * The range for the number of hours starting at the from date time
	 * @param from The date and time which the range should start
	 * @param dateTimeFormatter The date & time format to use to parse with
	 * @param hours The number of hours to mark the end of the range
	 * @return The date time range
	 */
	public static DateRange forHour(
		final String from, 
		final DateTimeFormatter dateTimeFormatter,
		final Long hours) {
	
		return forHour(
			LocalDateTime.parse(from, dateTimeFormatter), 
			hours);
	}

	/**
	 * The range for the number of hours starting at the from date time
	 * @param from The date and time which the range should start
	 * @return The date time range
	 */
	public static DateRange forHour(final LocalDateTime from, final Long hours) {
	
		return DateRange.Builder
				.from(from)
				.to(from.plusHours(hours))
				.build();
	}

	/**
	 * The range for the day
	 * @param date The day
	 * @return The date time range from the start of day to a second before midnight
	 */
	public static DateRange forDay(final String date) {
	
		return forDay(LocalDate.parse(date));
	}
	
	/**
	 * The date range for the week that the date is a member
	 * @param date The date within the specified week of the date range
	 * @return The date range from the start of Monday to Sunday night a second before midnight
	 */
	public static DateRange forWeek(final String date) {
		
		return forWeek(LocalDate.parse(date));
	}
	
	/**
	 * The range for the month that the date is a member
	 * @param date The date within the specified month of the date range
	 * @return The date range from the start of the month to the end of the month a second before midnight
	 */
	public static DateRange forMonth(final String date) {
		
		return forMonth(LocalDate.parse(date));
	}
	
	
	public static DateRange forDay(final LocalDate date) {
		
		return DateRange.Builder
				.fromStartOfDay(date)
				.toSecondBeforeMidnight(date)
				.build();
	}
	
	public static DateRange forWeek(final LocalDate date) {
		
		return DateRange.Builder
				.fromStartOfWeek(date)
				.toEndOfWeek(date)
				.build();
	}
	
	public static DateRange forMonth(final LocalDate date) {
		
		return DateRange.Builder
				.fromStartOfMonth(date)
				.toEndOfMonth(date)
				.build();
	}
}
