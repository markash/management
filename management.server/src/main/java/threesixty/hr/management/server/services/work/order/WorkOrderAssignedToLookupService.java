package threesixty.hr.management.server.services.work.order;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.server.services.lookup.AbstractLookupService;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;
import org.eclipse.scout.rt.shared.services.lookup.ILookupRow;

import threesixty.hr.management.shared.services.work.IWorkManager;
import threesixty.hr.management.shared.services.work.order.IWorkOrderAssignedToLookupService;
import threesixty.hr.management.shared.services.work.order.WorkOrder;

public class WorkOrderAssignedToLookupService extends AbstractLookupService<Long> implements IWorkOrderAssignedToLookupService {

	@Override
	public List<? extends ILookupRow<Long>> getDataByKey(
			final ILookupCall<Long> call) {
		
		return createRows(
				call,
				BEANS.get(IWorkManager.class).assignedToLookup(call));
	}

	@Override
	public List<? extends ILookupRow<Long>> getDataByText(
			final ILookupCall<Long> call) {
		
		
		return createRows(
				call,
				BEANS.get(IWorkManager.class).assignedToLookup(call));
	}

	@Override
	public List<? extends ILookupRow<Long>> getDataByAll(ILookupCall<Long> call) {
		
		return createRows(
				call,
				BEANS.get(IWorkManager.class).assignedToLookup(call));
	}

	@Override
	public List<? extends ILookupRow<Long>> getDataByRec(ILookupCall<Long> call) {
		
		return createRows(
				call,
				BEANS.get(IWorkManager.class).assignedToLookup(call));
	}

	public List<? extends ILookupRow<Long>> createRows(
			final ILookupCall<Long> call,
			final List<WorkOrder> workOrders) {
		
		Object[][] data = 
				workOrders.stream()
					.map(workOrder -> new Object[] { workOrder.getId(), workOrder.getName() })
					.collect(Collectors.toList())
					.toArray(new Object[0][]); 
		
		return createLookupRowArray(data, call, Long.class);
	}
}
