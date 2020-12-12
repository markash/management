package threesixty.hr.management.server.services.party.internalOrganization;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.rest.client.IRestResourceClient;

import threesixty.hr.management.server.services.party.PartyEntityDo;
import threesixty.hr.management.server.services.rest.RestClientHelper;
import threesixty.hr.management.shared.services.party.internalOrganization.InternalOrganization;

public class InternalOrganizationResourceClient implements IRestResourceClient {
	protected static final String RESOURCE_PATH = "internalOrganization";

	protected RestClientHelper helper() {
		return BEANS.get(RestClientHelper.class);
	}

	public List<InternalOrganization> retrieveAll() {
		
		WebTarget target = 
				helper().target(RESOURCE_PATH);
		
		List<InternalOrganization> results = target.request().accept(MediaType.APPLICATION_JSON)
				.get(Response.class)
				.readEntity(new GenericType<List<InternalOrganization>>() {});
		
		return results;
	}
	
	public InternalOrganization retrieve(
			final Long partyId) {
		
		WebTarget target = 
				helper().target(RESOURCE_PATH)
				.path("/{id}")
				.resolveTemplate("id", partyId);
		
		InternalOrganization party = target.request().accept(MediaType.APPLICATION_JSON)
				.get(Response.class)
				.readEntity(InternalOrganization.class);
		
		return party;
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