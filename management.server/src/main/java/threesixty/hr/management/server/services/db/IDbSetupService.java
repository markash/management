package threesixty.hr.management.server.services.db;

import org.eclipse.scout.rt.platform.service.IService;

public interface IDbSetupService extends IService {

	/**
	 * Migrate the database to the new version
	 */
	void migrate();
}
