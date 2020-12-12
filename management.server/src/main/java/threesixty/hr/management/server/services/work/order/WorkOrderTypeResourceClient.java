package threesixty.hr.management.server.services.work.order;

import java.util.List;

import javax.ws.rs.core.GenericType;

import threesixty.hr.management.server.services.rest.AbstractResourceClient;
import threesixty.hr.management.shared.services.work.order.WorkOrderType;

public class WorkOrderTypeResourceClient extends AbstractResourceClient<WorkOrderType, String> {

	protected static final String RESOURCE_PATH = "workOrderType";

	public WorkOrderTypeResourceClient() {
		super(
				RESOURCE_PATH, 
				WorkOrderType.class,
				new GenericType<List<WorkOrderType>>() {});
	}
}
