package threesixty.hr.server.services.search;

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

import threesixty.hr.application.model.party.PartyMapper;
import threesixty.hr.application.model.party.PersonDo;
import threesixty.hr.core.party.ExternalOrganization;
import threesixty.hr.core.party.Party;
import threesixty.hr.core.party.PartyName;
import threesixty.hr.core.party.Person;

@Path("/library")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LibraryResource {

	@Inject
    EntityManager em;
	
	@Inject
	PartyMapper partyMapper;
	
    @GET
    public List<PersonDo> list() {
 
    	LocalDate logicalDate = LocalDate.now();
    	
    	List<Person> persons = Person.listAll();
    	
    	List<Party> parties = ExternalOrganization.listAll();
    	parties.addAll(persons);
		
		List<PersonDo> results =
				persons.stream()
					.map(person -> partyMapper.toPerson(person, logicalDate))
					.collect(Collectors.toList());

		 return results;
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