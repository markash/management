package threesixty.hr.management.shared.services.work.timesheet;

import java.time.LocalDate;
import java.util.List;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;

@TunnelToServer
public interface ITimesheetEngine extends IService {
	
	/**
	 * Retrieve work orders filtered by the search filter
	 * @param filter The search filter
	 * @return The work order table page
	 */
//	WorkOrderTablePageData retrieveWorkOrders(
//			final SearchFilter filter);
	
	/**
	 * Prepares the work order form data for modification by loading the 
	 * work order into the form data instance.
	 *  
	 * @param formData The form data containing the work order id to load from the server
	 * @return The loaded form data
	 */
//	WorkOrderFormData prepareModify(
//			final WorkOrderFormData formData);
//	

	void persist(
			final TimesheetEntry entry);
	
	/**
	 * Retrieve the time sheet entries for the person between the from and to dates.
	 * @param submitterId The identifier of the person that submitted the timesheet
	 * @param fromDate The date and time from which to retrieve
	 * @param toDate The date and time to retrieve to
	 * @return The list of time sheet entries
	 */
	List<TimesheetEntry> retrieveBySubmitter(
			final Long submitterId,
			final LocalDate fromDate,
			final LocalDate toDate);
	
	/**
	 * Retrieve the time sheet entry for the person and time sheet id
	 * @param timesheetId The identifier of the time sheet
	 * @return The time sheet entry
	 */
	TimesheetEntry retrieve(
			final Long timesheetId);
}
