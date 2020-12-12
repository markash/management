package threesixty.hr.server.services;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

import io.quarkus.runtime.StartupEvent;
import io.quarkus.test.Mock;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import threesixty.hr.infrastructure.database.migration.MigrationManager;

@Mock @ApplicationScoped
public class StubbedMigrationManager extends MigrationManager{ 
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	
	public void onStartup(
			final @Observes StartupEvent event) {
			
		log.info("Test");
	}
}
