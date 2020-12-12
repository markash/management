package threesixty.hr.management.client;

import java.net.URL;

import org.eclipse.scout.rt.client.services.common.icon.AbstractIconProviderService;

/**
 * @author USER
 */
public class DefaultIconProviderService extends AbstractIconProviderService {
	@Override
	protected URL findResource(String relativePath) {
		return ResourceBase.class.getResource("icons/" + relativePath);
	}
}
