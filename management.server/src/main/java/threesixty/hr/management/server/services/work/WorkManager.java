package threesixty.hr.management.server.services.work;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;

import threesixty.hr.management.server.services.work.order.WorkOrderResourceClient;
import threesixty.hr.management.server.services.work.order.WorkOrderToFormData;
import threesixty.hr.management.shared.exception.IdentifierNotSetVetoException;
import threesixty.hr.management.shared.exception.WorkOrderNotFoundVetoException;
import threesixty.hr.management.shared.services.work.IWorkManager;
import threesixty.hr.management.shared.services.work.order.IWorkOrderEngine;
import threesixty.hr.management.shared.services.work.order.WorkOrder;
import threesixty.hr.management.shared.services.work.order.WorkOrderToRowData;
import threesixty.hr.management.shared.services.work.order.WorkOrderType;
import threesixty.hr.management.shared.work.order.WorkOrderFormData;
import threesixty.hr.management.shared.work.order.WorkOrderTablePageData;
import threesixty.hr.management.shared.work.order.WorkOrderTablePageData.WorkOrderTableRowData;

public class WorkManager implements IWorkManager {
	
	/**
	 * Retrieve work orders filtered by the search filter
	 * @param filter The search filter
	 * @return The work order table page
	 */
	@Override
	public WorkOrderTablePageData retrieveWorkOrders(
			final SearchFilter filter) {
		
		WorkOrderToRowData mapper = new WorkOrderToRowData();
		
		WorkOrderTableRowData[] rows =
				BEANS.get(IWorkOrderEngine.class)
					.retrieve(filter)
					.stream()
					.map(mapper)
					.collect(Collectors.toList())
					.toArray(new WorkOrderTableRowData[0]);
		
		WorkOrderTablePageData pageData = new WorkOrderTablePageData();
		pageData.setRows(rows);
		
		return pageData;
	}
	
	/**
	 * Prepares the work order form data for modification by loading the 
	 * work order into the form data instance.
	 *  
	 * @param formData The form data containing the work order id to load from the server
	 * @return The loaded form data
	 */
	@Override
	public WorkOrderFormData prepareModify(
			final WorkOrderFormData formData) {
		
		if (formData.getId() == null) {
			throw new IdentifierNotSetVetoException("Work Order");
		}
		
		WorkOrderToFormData toFormDataMapper = 
				WorkOrderToFormData.withFormData(formData);
		
		WorkOrder workOrder = 
				BEANS.get(WorkOrderResourceClient.class)
					.retrieveById(formData.getId());
		
		return Optional.ofNullable(workOrder)
				.map(toFormDataMapper)
				.orElseThrow(() -> new WorkOrderNotFoundVetoException(formData.getId()));
	}
	
	@Override
	public WorkOrderFormData executeModify(
			final WorkOrderFormData formData) {
		
		WorkOrderType type = 
				WorkOrderType.builder()
					.name(formData.getWorkOrderType().getValue())
					.build();
		
		WorkOrder workOrder = new WorkOrder();
		workOrder.setId(formData.getId());
		workOrder.setType(type);
		
		WorkOrder result =
				BEANS.get(WorkOrderResourceClient.class)
					.update(workOrder);
		
		return formData;
	}
	
	/**
	 * Lookup the work order/s for a specific call
	 * @param call The lookup call
	 * @return The results
	 */
	public List<WorkOrder> assignedToLookup(
			final ILookupCall<Long> call) {
		
		final Long assignedToPartyId = call.getRec();
				
		if (assignedToPartyId == null) {
			throw new VetoException("The assigned to identifier is required");
		}
		
		return BEANS.get(IWorkOrderEngine.class)
				.retrieve(new SearchFilter())
				.stream()
				.filter(workOrder -> workOrder.isAssignedTo(assignedToPartyId))
				.collect(Collectors.toList());
	}
}
