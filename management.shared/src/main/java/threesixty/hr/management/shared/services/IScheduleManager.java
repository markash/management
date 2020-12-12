package threesixty.hr.management.shared.services;

import java.time.LocalDate;
import java.util.List;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;
import org.eclipse.scout.rt.shared.services.common.calendar.ICalendarAppointment;

import threesixty.hr.management.shared.employee.TimesheetFormData;

@TunnelToServer
public interface IScheduleManager extends IService {

	
	List<ICalendarAppointment> retrieveAppointments(
			final Long personId,
			final LocalDate from,
			final LocalDate to);
	
	
	void prepareNew(
			final TimesheetFormData formData);
	
	TimesheetFormData prepareModify(
			final TimesheetFormData formData);
	
	void record(
			final TimesheetFormData formData);
}
