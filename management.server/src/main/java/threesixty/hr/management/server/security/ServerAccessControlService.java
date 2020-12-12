package threesixty.hr.management.server.security;

import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Replace;
import org.eclipse.scout.rt.security.DefaultPermissionCollection;
import org.eclipse.scout.rt.security.IPermissionCollection;
import org.eclipse.scout.rt.security.PermissionLevel;
import org.eclipse.scout.rt.shared.security.RemoteServiceAccessPermission;

import threesixty.hr.management.shared.services.security.AccessControlService;

/**
 * @author USER
 */
@Replace
public class ServerAccessControlService extends AccessControlService {

	@Override
	protected IPermissionCollection execLoadPermissions(String userId) {
		IPermissionCollection permissions = BEANS.get(DefaultPermissionCollection.class);
		permissions.add(new RemoteServiceAccessPermission("*.shared.*", "*"), PermissionLevel.ALL);

		// TODO [USER]: Fill access control service - or replace this default
		// implementation by simply return BEANS.get(AllPermissionCollection.class)

		permissions.setReadOnly();
		return permissions;
	}
}
