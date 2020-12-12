package threesixty.hr.management.shared.services.work.timesheet;

import java.util.Arrays;

import threesixty.hr.management.shared.services.ICodes;

public enum TimesheetPeriods implements ICodes<String> {

	MINUTE("minute"),
	HOUR("hour"),
	DAY("day"),
	MONTH("month"),
	PERIOD("period");
	
	private final String id;
	
	private TimesheetPeriods( 
			final String id) {
		
		this.id = id;
	}
	
	public String getId() { return id; }

	/**
	 * Retrieve the period type based upon the id
	 * @param id The period id
	 * @return The timesheet period
	 */
	public static TimesheetPeriods resolveId(final String id) {
		
		return Arrays.stream(TimesheetPeriods.values())
				.filter(type -> type.getId().equalsIgnoreCase(id))
				.findAny()
				.orElse(null);
	}
}
