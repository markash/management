package threesixty.hr.management.server.services.db.derby;

import org.eclipse.scout.rt.platform.BEANS;
import org.flywaydb.core.Flyway;

import threesixty.hr.management.server.services.common.sql.DerbySqlService;
import threesixty.hr.management.server.services.db.IDbSetupService;

public class DerbyDbSetupService implements IDbSetupService {

	@Override
	public void migrate() {
		
		final String url = 
				BEANS.get(DerbySqlService.class)
					.getJdbcMappingName();
		
		Flyway flyway = 
				Flyway.configure()
					.locations("classpath:/db/derby")
					.dataSource(url, null, null)
					.load();
		
		//flyway.clean();
		flyway.migrate();
	}
}
