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
import threesixty.hr.application.model.party.PartyMapper;
import threesixty.hr.application.model.party.PersonDo;
import threesixty.hr.core.party.Party;
import threesixty.hr.core.party.PartyName;
import threesixty.hr.core.party.Person;

@Path("/person")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {

	@Inject
    EntityManager em;
	
	@Inject
	PartyMapper partyMapper;
	
    @GET
    public Multi<PersonDo> list() {
 
    	LocalDate logicalDate = LocalDate.now();
    	
    	List<Person> persons = Person.listAll();
    	
		List<PersonDo> results =
				persons.stream()
					.map(person -> partyMapper.toPerson(person, logicalDate))
					.collect(Collectors.toList());

		 return Multi.createFrom().items(results.stream());
    }
    
    @GET
    @Path("/{id}")
    public Uni<PersonDo> single(final @PathParam("id") Long id) {
    	
    	LocalDate logicalDate = LocalDate.now();
    	
    	PersonDo result = 
    			Optional.ofNullable((Person) Person.findById(id))
    				.map(person -> partyMapper.toPerson(person, logicalDate))
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