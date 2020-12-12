package threesixty.hr.management.server.services.work.order;

import java.util.List;

import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

import threesixty.hr.management.shared.services.work.order.IWorkOrderEngine;
import threesixty.hr.management.shared.services.work.order.WorkOrder;
import threesixty.hr.management.shared.util.CollectorUtility;
import threesixty.hr.management.shared.work.order.WorkOrderTablePageData;


public class WorkOrderEngine implements IWorkOrderEngine {

	@Override
	public List<WorkOrder> retrieve(
			final SearchFilter filter) {
		
		return BEANS.get(WorkOrderResourceClient.class).retrieveAll();
	}
	
	@Override
	public WorkOrderTablePageData retrievePageData(
			final SearchFilter filter) {
		
		return retrieve(filter)
				.stream()
				.map(new WorkOrderToRowDataMapper())
				.collect(CollectorUtility.toWorkOrderTablePageData());
	}
}
