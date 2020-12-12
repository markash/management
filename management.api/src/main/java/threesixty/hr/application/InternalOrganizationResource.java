package threesixty.hr.application;

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

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import threesixty.hr.application.model.party.InternalOrganizationDo;
import threesixty.hr.application.model.party.PartyMapper;
import threesixty.hr.core.party.InternalOrganization;
import threesixty.hr.core.party.Party;
import threesixty.hr.core.party.PartyName;

@Path("/internalOrganization")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InternalOrganizationResource {

	@Inject
    EntityManager em;
	
	@Inject
	PartyMapper partyMapper;
	
    @GET
    public Multi<InternalOrganizationDo> list() {
 
    	LocalDate logicalDate = LocalDate.now();
    	
    	List<InternalOrganization> internalOrganizations = InternalOrganization.listAll();
    	
		List<InternalOrganizationDo> results =
				internalOrganizations.stream()
					.map(organization -> partyMapper.toInternalOrganization(organization, logicalDate))
					.collect(Collectors.toList());

		 return Multi.createFrom().items(results.stream());
    }
    
    @GET
    @Path("/{id}")
    public Uni<InternalOrganizationDo> single(final @PathParam("id") Long id) {
    	
    	LocalDate logicalDate = LocalDate.now();
    	
    	InternalOrganizationDo result = 
    			Optional.ofNullable((InternalOrganization) InternalOrganization.findById(id))
    				.map(organization -> partyMapper.toInternalOrganization(organization, logicalDate))
    				.orElse(null);
    	
		 return Uni.createFrom().item(result);
    }
    
    @GET()
    @Path("/invalid")
    public List<PartyName> invalid() {
    	
    	return Party.listAll().stream()
    		.map(party -> ((Party) party).getInvalidPartyNames())
    		.flatMap(l -> l.stream())
    		.collect(Collectors.toList());
    }
}