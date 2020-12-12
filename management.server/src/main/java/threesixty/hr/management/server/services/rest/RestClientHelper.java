package threesixty.hr.management.server.services.rest;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ContextResolver;

import org.eclipse.scout.rt.platform.ApplicationScoped;
import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.rest.client.AbstractRestClientHelper;
import org.eclipse.scout.rt.rest.error.ErrorDo;
import org.eclipse.scout.rt.rest.error.ErrorResponse;

@ApplicationScoped
public class RestClientHelper extends AbstractRestClientHelper {

	@Override
	protected String getBaseUri() {
		return "http://localhost:9191/";
	}

	@Override @SuppressWarnings("rawtypes")
	protected List<ContextResolver> getContextResolversToRegister() {
		
		return Arrays.asList(new ObjectMapperContextResolver());
	}
	
	@Override
	protected RuntimeException transformException(final RuntimeException e, final Response response) {

		if (response != null && response.hasEntity()) {

			ErrorDo error = response.readEntity(ErrorResponse.class).getError();

			throw new VetoException(error.getMessage()).withTitle(error.getTitle());
		}

		return e;
	}
}