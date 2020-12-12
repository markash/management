package threesixty.hr.management.shared.services.work.timesheet;

import java.util.function.Function;

import org.eclipse.scout.rt.shared.services.common.calendar.CalendarAppointment;

import threesixty.hr.management.shared.util.LocalDateUtility;

public class TimesheetEntryToCalendarAppointment implements Function<TimesheetEntry, CalendarAppointment> {

	@Override
	public CalendarAppointment apply(
			final TimesheetEntry timesheetEntry) {
		
        return new CalendarAppointment(
        		timesheetEntry.getId(), 
        		timesheetEntry.getPersonId(), 
        		LocalDateUtility.toDate(timesheetEntry.getActivityStart()), 
        		LocalDateUtility.toDate(timesheetEntry.getActivityEnd()), 
        		false, 
        		"Timesheet: Work Order Name", 
        		"This appointment takes the full day", 
        		"calendar-timesheet");
	}

	
}
