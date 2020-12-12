package threesixty.hr.application.model.work.timesheet;

import java.time.LocalDate;
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
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import io.smallrye.mutiny.Uni;
import threesixty.hr.core.date.DateRange;
import threesixty.hr.core.date.DateRangeUtility;
import threesixty.hr.core.work.timesheet.Timesheet;

@Path("/timesheet")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TimesheetResource {

	@Inject
    EntityManager em;
	
	@Inject
	TimesheetMapper mapper;
	
    @GET
    public List<TimesheetDo> list() {
 
    	List<Timesheet> results = Timesheet.listAll();
    	
    	return results.stream()
    			.map(t -> mapper.toTimesheet(t))
    			.collect(Collectors.toList());
    }
		
    @GET
    @Path("/{id}")
    public Uni<TimesheetDo> single(
    		final @PathParam("id") Long id) {
    	
    	TimesheetDo result = 
    			Optional.ofNullable((Timesheet) Timesheet.findById(id))
    				.map(timesheet -> mapper.toTimesheet(timesheet))
    				.orElse(null);
    	
		 return Uni.createFrom().item(result);
    }
    
    @GET
    @Path("/submitter/{id}/day/{day}")
    public List<TimesheetDo> submitterForDay(
    		final @PathParam("id") Long partyId,
    		final @PathParam("day") String day) {
    	
    	return submitterForDateRange(
    			partyId, 
    			DateRangeUtility.forDay(day));
    }
    
    @GET
    @Path("/submitter/{id}/week/{day}")
    public List<TimesheetDo> submitterForWeek(
    		final @PathParam("id") Long partyId,
    		final @PathParam("day") String day) {
    	
    	return submitterForDateRange(
    			partyId, 
    			DateRangeUtility.forWeek(day));
    }
    
    @GET
    @Path("/submitter/{id}/month/{day}")
    public List<TimesheetDo> submitterForMonth(
    		final @PathParam("id") Long partyId,
    		final @PathParam("day") String day) {
    	
    	return submitterForDateRange(
    			partyId, 
    			DateRangeUtility.forMonth(day));
    }
	
	@GET
    @Path("/submitter/{id}/period")
    public List<TimesheetDo> submitterForDay(
    		final @PathParam("id") Long partyId,
			final @QueryParam("from") String from,
			final @QueryParam("to") String to) {
		
		final LocalDate fromDate = LocalDate.parse(from);
		final LocalDate toDate = LocalDate.parse(to);

    	return submitterForDateRange(
    			partyId, 
				DateRange.Builder
					.fromStartOfDay(fromDate)
					.toSecondBeforeMidnight(toDate)
					.build());
	}
	
    public List<TimesheetDo> submitterForDateRange(
    		final Long partyId,
    		final DateRange dateRange) {
    	
    	return Timesheet.findBySubmitter(partyId, dateRange)
    				.stream()
    				.map(t -> mapper.toTimesheet(t))
    				.collect(Collectors.toList());
    }
}