package threesixty.hr.application;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import threesixty.hr.application.model.work.WorkMapper;
import threesixty.hr.application.model.work.order.WorkOrderRoleTypeDo;
import threesixty.hr.core.work.order.WorkOrderRoleType;

@Path("/workOrderRoleType")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WorkOrderRoleTypeResource {

	@Inject
    EntityManager em;
	
	@Inject
	WorkMapper workOrderMapper;
	
    @GET
    public List<WorkOrderRoleTypeDo> list() {
 
    	LocalDate logicalDate = LocalDate.now();
    	
    	List<WorkOrderRoleType> results = WorkOrderRoleType.listAll();
    	
    	return results.stream()
    			.map(t -> workOrderMapper.toWorkOrderRoleTypeDo(t))
    			.collect(Collectors.toList());
    }
}