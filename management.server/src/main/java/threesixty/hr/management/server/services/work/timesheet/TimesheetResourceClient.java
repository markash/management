package threesixty.hr.management.server.services.work.timesheet;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.ws.rs.core.GenericType;

import org.eclipse.scout.rt.platform.holders.NVPair;

import threesixty.hr.management.server.services.rest.AbstractResourceClient;
import threesixty.hr.management.server.services.rest.RestParam;
import threesixty.hr.management.shared.services.work.timesheet.TimesheetEntry;
import threesixty.hr.management.shared.services.work.timesheet.TimesheetPeriods;

public class TimesheetResourceClient extends AbstractResourceClient<TimesheetEntry, Long> {

	protected static final String RESOURCE_PATH = "timesheet";

	public TimesheetResourceClient() {
		super(
				RESOURCE_PATH, 
				TimesheetEntry.class,
				new GenericType<List<TimesheetEntry>>() {});
	}
	
	/**
	 * Retrieve the resource by submitter id for a specified period
	 * @param submitterId The id of the submitter
	 * @param timesheetPeriod The type of timesheet period, i.e. Day, Week, Month, Quarter, Biannual, Annual
	 * @param referenceDate The reference date within the timesheet period
	 * @return The list of timesheets for the period for the submitter
	 */
	public List<TimesheetEntry> retrieveBySubmitterId(
			final Long submitterId,
			final TimesheetPeriods timesheetPeriod,
			final LocalDate referenceDate) {
		
		return query("/submitter/{submitterId}/{timesheetPeriod}/{referenceDate}",
				new NVPair("submitterId", submitterId),
				new NVPair("timesheetPeriod", timesheetPeriod.getId()),
				new NVPair("referenceDate", referenceDate.format(DateTimeFormatter.ISO_DATE)));
	}
	
	/**
	 * Retrieve the resource by submitter id for a specified period
	 * @param submitterId The id of the submitter
	 * @param timesheetPeriod The type of timesheet period, i.e. Day, Week, Month, Quarter, Biannual, Annual
	 * @param referenceDate The reference date within the timesheet period
	 * @return The list of timesheets for the period for the submitter
	 */
	public List<TimesheetEntry> retrieveBySubmitterId(
			final Long submitterId,
			final LocalDate fromDate,
			final LocalDate toDate) {
		
		return query("/submitter/{submitterId}/{timesheetPeriod}",
				RestParam.Builder.pathParameter("submitterId").value(submitterId).build(),
				RestParam.Builder.pathParameter("timesheetPeriod").value(TimesheetPeriods.PERIOD.getId()).build(),
				RestParam.Builder.queryParameter("from").value(fromDate).build(),
				RestParam.Builder.queryParameter("to").value(toDate).build());
	}
}
