package threesixty.hr.management.server.services.party.person;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.scout.rt.platform.exception.VetoException;

import threesixty.hr.management.server.services.party.PartyEntityDo;
import threesixty.hr.management.server.services.rest.AbstractResourceClient;
import threesixty.hr.management.shared.services.party.person.Person;

public class PersonResourceClient extends AbstractResourceClient<Person, Long> {
	
	protected static final String RESOURCE_PATH = "person";
	
	public PersonResourceClient() {
		super(
				RESOURCE_PATH, 
				Person.class,
				new GenericType<List<Person>>() {});
	}

	public PartyEntityDo updateExampleEntity(
			final String id, 
			final PartyEntityDo entity) {
		
		WebTarget target = helper().target(RESOURCE_PATH).path("/{id}").resolveTemplate("id", id);
		return target.request().accept(MediaType.APPLICATION_JSON).post(Entity.json(entity), PartyEntityDo.class);
	}

	public void deleteExampleEntity(
			final String id) {
		
		WebTarget target = helper().target(RESOURCE_PATH).path("/{id}").resolveTemplate("id", id);
		Response response = target.request().delete();
		response.close();
	}

	public PartyEntityDo getExampleEntityCustomExceptionHandling(String id) {
		
		WebTarget target = helper().target(RESOURCE_PATH, this::transformCustomException).path("/{id}")
				.resolveTemplate("id", id);
		return target.request().accept(MediaType.APPLICATION_JSON).get(PartyEntityDo.class);
	}

	protected RuntimeException transformCustomException(RuntimeException e, Response r) {
		if (r != null && r.hasEntity() && MediaType.TEXT_PLAIN_TYPE.equals(r.getMediaType())) {
			String message = r.readEntity(String.class);
			throw new VetoException(message);
		}
		return e;
	}
}