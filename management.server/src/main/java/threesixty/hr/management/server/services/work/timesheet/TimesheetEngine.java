package threesixty.hr.management.server.services.work.timesheet;

import java.time.LocalDate;
import java.util.List;

import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

import threesixty.hr.management.shared.services.work.timesheet.ITimesheetEngine;
import threesixty.hr.management.shared.services.work.timesheet.TimesheetEntry;
import threesixty.hr.management.shared.services.work.timesheet.TimesheetPeriods;

public class TimesheetEngine implements ITimesheetEngine {
	
	public List<TimesheetEntry> retrieve(
			final SearchFilter filter) {
		
		return BEANS.get(TimesheetResourceClient.class)
				.retrieveAll();
	}
	
	/**
	 * Retrieve work orders filtered by the search filter
	 * @param filter The search filter
	 * @return The work order table page
	 */
	//@Override
	public List<TimesheetEntry> retrieveTimesheetsForDay(
			final Long submitterId,
			final LocalDate referenceDate) {
		
		return BEANS.get(TimesheetResourceClient.class)
				.retrieveBySubmitterId(
					submitterId, 
					TimesheetPeriods.DAY, 
					referenceDate);
	}
	
	/**
	 * Retrieve the time sheet entry for the person and time sheet id
	 * @param timesheetId The identifier of the time sheet
	 * @return The time sheet entry
	 */
	//@Override
	public TimesheetEntry retrieve(
			final Long timesheetId) {
		
		return BEANS.get(TimesheetResourceClient.class)
				.retrieveAll()
				.stream()
				.filter(item -> item.getId().equals(timesheetId))
				.findFirst()
				.orElse(null);
	}

	@Override
	public void persist(TimesheetEntry entry) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<TimesheetEntry> retrieveBySubmitter(
			final Long submitterId, 
			final LocalDate fromDate, 
			final LocalDate toDate) {
		
		return BEANS.get(TimesheetResourceClient.class)
				.retrieveBySubmitterId(
					submitterId, 
					fromDate, 
					toDate);
	}
	
	/**
	 * Prepares the work order form data for modification by loading the 
	 * work order into the form data instance.
	 *  
	 * @param formData The form data containing the work order id to load from the server
	 * @return The loaded form data
	 */
	//@Override
//	public WorkOrderFormData prepareModify(
//			final WorkOrderFormData formData) {
		
//		if (formData.getId().getValue() == null) {
//			throw new IdentifierNotSetVetoException("Work Order");
//		}
//		
//		WorkOrderToFormData toFormDataMapper = 
//				WorkOrderToFormData.withFormData(formData);
//		
//		WorkOrder workOrder = 
//				BEANS.get(TimesheetResourceClient.class)
//					.retrieveById(formData.getId().getValue());
//		
//		return Optional.ofNullable(workOrder)
//				.map(toFormDataMapper)
//				.orElseThrow(() -> new WorkOrderNotFoundVetoException(formData.getId().getValue()));
		
//		return null;
//	}
	
	/**
	 * Lookup the work order/s for a specific call
	 * @param call The lookup call
	 * @return The results
	 */
//	public List<WorkOrder> assignedToLookup(
//			final ILookupCall<Long> call) {
		
//		final Long assignedToPartyId = call.getRec();
//				
//		if (assignedToPartyId == null) {
//			throw new VetoException("The assigned to identifier is required");
//		}
//		
//		return retrieve(new SearchFilter())
//				.stream()
//				.filter(workOrder -> workOrder.isAssignedTo(assignedToPartyId))
//				.collect(Collectors.toList());
		
//		return null;
//	}
}
