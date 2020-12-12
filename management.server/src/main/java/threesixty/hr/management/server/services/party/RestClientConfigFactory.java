package threesixty.hr.management.server.services.party;

import javax.ws.rs.core.Configuration;

import org.eclipse.scout.rt.rest.client.IRestClientConfigFactory;
import org.glassfish.jersey.client.ClientConfig;

public class RestClientConfigFactory implements IRestClientConfigFactory {

	@Override
	public Configuration createClientConfig() {
		
		return new ClientConfig();
	}
}
