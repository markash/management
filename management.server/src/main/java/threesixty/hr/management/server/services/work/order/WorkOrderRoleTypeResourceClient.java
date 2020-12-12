package threesixty.hr.management.server.services.work.order;

import java.util.List;

import javax.ws.rs.core.GenericType;

import threesixty.hr.management.server.services.rest.AbstractResourceClient;
import threesixty.hr.management.shared.services.work.order.WorkOrderRoleType;

public class WorkOrderRoleTypeResourceClient extends AbstractResourceClient<WorkOrderRoleType> {

	protected static final String RESOURCE_PATH = "workOrderRoleType";

	public WorkOrderRoleTypeResourceClient() {
		super(
				RESOURCE_PATH, 
				WorkOrderRoleType.class,
				new GenericType<List<WorkOrderRoleType>>() {});
	}
}
