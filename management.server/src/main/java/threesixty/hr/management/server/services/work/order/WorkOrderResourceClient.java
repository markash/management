package threesixty.hr.management.server.services.work.order;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.rest.client.IRestResourceClient;

import threesixty.hr.management.server.services.rest.RestClientHelper;
import threesixty.hr.management.shared.services.work.order.WorkOrder;

public class WorkOrderResourceClient implements IRestResourceClient {

	protected static final String RESOURCE_PATH = "workOrder";

	protected RestClientHelper helper() {
		return BEANS.get(RestClientHelper.class);
	}
	
	/**
	 * Retrieve the work orders
	 * @return List of work orders
	 */
	public List<WorkOrder> retrieveAll() {
		
		return helper()
				.target(RESOURCE_PATH)
				.request()
				.accept(MediaType.APPLICATION_JSON)
				.get(Response.class)
				.readEntity(new GenericType<List<WorkOrder>>() {});
	}
	
	/**
	 * Retrieve the work order by id
	 * @return The work order identified by the id or null
	 */
	public WorkOrder retrieveById(
			final Long id) {
		
		return helper()
				.target(RESOURCE_PATH)
				.path("/{id}").resolveTemplate("id", id)
				.request()
				.accept(MediaType.APPLICATION_JSON)
				.get(Response.class)
				.readEntity(WorkOrder.class);
	}
	
	public WorkOrder update(
			final WorkOrder workOrder) {
		
		WebTarget target = 
				helper()
				.target(RESOURCE_PATH)
				.path("/{id}").resolveTemplate("id", workOrder.getId());
				
		return target.request()
				.accept(MediaType.APPLICATION_JSON)
				.post(Entity.json(workOrder), WorkOrder.class);
	}
}
