package threesixty.hr.management.server.services.work.order;

import java.util.function.Function;

import threesixty.hr.management.shared.services.work.order.WorkOrder;
import threesixty.hr.management.shared.work.order.WorkOrderTablePageData.WorkOrderTableRowData;

public class WorkOrderToRowDataMapper implements Function<WorkOrder, WorkOrderTableRowData> {

	@Override
	public WorkOrderTableRowData apply(
			final WorkOrder workOrder) {
		
		WorkOrderTableRowData rowData = new WorkOrderTableRowData();
		rowData.setId(workOrder.getId());
		rowData.setName(workOrder.getName());
		rowData.setScheduledStart(workOrder.getScheduledStartDate());
		rowData.setScheduledEnd(workOrder.getScheduledEndDate());
		rowData.setActualStart(workOrder.getActualStartDate());
		rowData.setActualEnd(workOrder.getActualEndDate());
		rowData.setEstimate(workOrder.getEstimate());
		rowData.setCapitalize(workOrder.getCapitalize());
		
		return rowData;
	}
}
