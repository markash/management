package threesixty.hr.management.shared.services.work;

import java.util.List;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;

import threesixty.hr.management.shared.services.work.order.WorkOrder;
import threesixty.hr.management.shared.services.work.order.WorkOrderType;
import threesixty.hr.management.shared.work.order.WorkOrderFormData;
import threesixty.hr.management.shared.work.order.WorkOrderTablePageData;

@TunnelToServer
public interface IWorkManager extends IService {

	/**
	 * Retrieve work orders filtered by the search filter
	 * @param filter The search filter
	 * @return The work order table page
	 */
	WorkOrderTablePageData retrieveWorkOrders(
			final SearchFilter filter);
	
	/**
	 * Prepares the work order form data for modification by loading the 
	 * work order into the form data instance.
	 *  
	 * @param formData The form data containing the work order id to load from the server
	 * @return The loaded form data
	 */
	WorkOrderFormData prepareModify(
			final WorkOrderFormData formData);
	
	WorkOrderFormData executeModify(
			final WorkOrderFormData formData);
	
	/**
	 * Lookup the work order/s assigned to a specific party
	 * @param call The lookup call with the master object containing the party id
	 * @return The results
	 */
	List<WorkOrder> assignedToLookup(
			final ILookupCall<Long> call);
	
	/**
	 * Retrieve the work order type by name
	 * @param workOrderTypeName The name of the work order type
	 * @return The work order type or null
	 */
	WorkOrderType retrieveWorkOrderType(
			final String workOrderTypeName);
}
