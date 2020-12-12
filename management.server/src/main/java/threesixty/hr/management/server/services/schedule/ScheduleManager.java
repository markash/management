package threesixty.hr.management.server.services.schedule;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.shared.services.common.calendar.ICalendarAppointment;

import threesixty.hr.management.shared.employee.TimesheetFormData;
import threesixty.hr.management.shared.services.IScheduleManager;
import threesixty.hr.management.shared.services.work.timesheet.ITimesheetEngine;
import threesixty.hr.management.shared.services.work.timesheet.TimesheetEntry;
import threesixty.hr.management.shared.services.work.timesheet.TimesheetEntryToCalendarAppointment;
import threesixty.hr.management.shared.util.LocalDateUtility;

public class ScheduleManager implements IScheduleManager {

	public void record(
			final TimesheetEntry entry) {
		
		BEANS.get(ITimesheetEngine.class).persist(entry);
	}
	
	@Override
	public List<ICalendarAppointment> retrieveAppointments(
			final Long submitterId,
			final LocalDate from,
			final LocalDate to) {
	
		List<TimesheetEntry> entries = 
				BEANS.get(ITimesheetEngine.class)
					.retrieveBySubmitter(submitterId, from, to);
		
		List<ICalendarAppointment> results =
			entries.stream()
				.map(new TimesheetEntryToCalendarAppointment())
				.collect(Collectors.toList());
		
		return results;
	}
	
	@Override
	public void prepareNew(
			final TimesheetFormData formData) {
		
		formData.getPerson().setValue(1L);
	}
	
	@Override
	public TimesheetFormData prepareModify(
			final TimesheetFormData formData) {
		
		TimesheetEntry entry = 
				BEANS.get(ITimesheetEngine.class)
					.retrieve(
							formData.getId().getValue());
		
		formData.getId().setValue(entry.getId());
		formData.getPerson().setValue(entry.getPersonId());
		formData.getDate().setValue(LocalDateUtility.toDate(entry.getActivityStart().toLocalDate()));
		formData.getStartTime().setValue(LocalDateUtility.toDate(entry.getActivityStart()));
		formData.getEndTime().setValue(LocalDateUtility.toDate(entry.getActivityEnd()));
		formData.getDuration().setValue(null);
		
		return formData;
	}
	
	public void record(
			final TimesheetFormData formData) {
		
		TimesheetEntry entry = new TimesheetEntry();
		
		formData.getDate().setValue(LocalDateUtility.toDate(entry.getActivityStart().toLocalDate()));
		formData.getStartTime().setValue(LocalDateUtility.toDate(entry.getActivityStart()));
		formData.getEndTime().setValue(LocalDateUtility.toDate(entry.getActivityEnd()));
		entry.setPersonId(formData.getPerson().getValue());
		
		BEANS.get(ITimesheetEngine.class).persist(entry);
	}
}
