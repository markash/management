package threesixty.hr.application.model.work.order;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.smallrye.mutiny.Uni;
import threesixty.hr.application.ResourcePaths;
import threesixty.hr.application.model.work.WorkMapper;
import threesixty.hr.core.work.order.IModifyWorkOrder;
import threesixty.hr.core.work.order.IRetrieveWorkOrder;
import threesixty.hr.core.work.order.WorkOrder;

@Path(ResourcePaths.WORK_ORDER)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WorkOrderResource {
	private static final Logger LOG = LoggerFactory.getLogger(WorkOrderResource.class);
	
	@Inject
    EntityManager em;
	
	@Inject
	WorkMapper workOrderMapper;
	
	@Inject
	IRetrieveWorkOrder retrieveWorkOrder;
	
	@Inject
	IModifyWorkOrder modifyWorkOrder;
	
    @GET
    public List<WorkOrderDo> list() {
 	
    	return retrieveWorkOrder
    			.retrieveAll()
    			.stream()
    			.map(t -> workOrderMapper.toWorkOrderDo(t))
    			.collect(Collectors.toList());
    }
    
    @GET
    @Path("/{id}")
    public Uni<WorkOrderDo> single(
    		final @PathParam("id") Long id) {
    
    	WorkOrderDo result = 
    			Optional.ofNullable((WorkOrder) retrieveWorkOrder.retrieveById(id))
    				.map(workOrder -> workOrderMapper.toWorkOrderDo(workOrder))
    				.orElse(null);
    	
		 return Uni.createFrom().item(result);
    }
    
    @POST
    @Path("/{id}")
    public Uni<WorkOrderDo> update(
    		final @PathParam("id") Long id,
    		final WorkOrderDo workOrderDo) {
    	
    	LOG.info("Work order update: {}", id);
    	
    	WorkOrder workOrder = workOrderMapper.toWorkOrder(workOrderDo);
    	
    	workOrder = modifyWorkOrder.modify(workOrder);
    	
		return Uni.createFrom().item(workOrderMapper.toWorkOrderDo(workOrder));
    }
}