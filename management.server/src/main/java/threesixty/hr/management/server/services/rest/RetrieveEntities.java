package threesixty.hr.management.server.services.rest;

import java.util.List;
import java.util.function.Function;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import threesixty.hr.management.shared.services.TypeEntity;

/**
 * Retrieves the list of entities from the web target
 * @author Mark Ashworth
 *
 * @param <T> The type for the list of entities
 */
public class RetrieveEntities<T> implements Function<WebTarget, List<T>> {

	@Override
	public List<T> apply(
			final WebTarget target) {
		
		GenericType<List<TypeEntity>> type = new GenericType<List<TypeEntity>>() {};
		
		List<TypeEntity> results = target.request().accept(MediaType.APPLICATION_JSON)
				.get(Response.class)
				.readEntity(type);
		
		return (List<T>) results;
	}
}