package threesixty.hr.application;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

import io.smallrye.mutiny.Uni;
import threesixty.hr.application.model.work.WorkMapper;
import threesixty.hr.application.model.work.order.WorkOrderTypeDo;
import threesixty.hr.core.work.order.WorkOrderType;

@Path("/workOrderType")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WorkOrderTypeResource {

	@Inject
    EntityManager em;
	
	@Inject
	WorkMapper workOrderMapper;
	
    @GET
    public List<WorkOrderTypeDo> list() {
 
    	List<WorkOrderType> results = WorkOrderType.listAll();
    	
    	return results.stream()
    			.map(t -> workOrderMapper.toWorkOrderTypeDo(t))
    			.collect(Collectors.toList());
    }
    
    @GET
    @Path("/{name}")
    public Uni<WorkOrderTypeDo> single(
    		final @PathParam("name") String name) {
    	
    	WorkOrderTypeDo result = 
    			Optional.ofNullable((WorkOrderType) WorkOrderType.findById(name))
    				.map(workOrder -> workOrderMapper.toWorkOrderTypeDo(workOrder))
    				.orElse(null);
    	
		 return Uni.createFrom().item(result);
    }
    
}