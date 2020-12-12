package threesixty.hr.infrastructure.database.migration;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.flywaydb.core.Flyway;

import io.quarkus.runtime.StartupEvent;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

@ApplicationScoped
public class MigrationManager {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Inject
	Flyway flyway;
	
	public void onStartup(
			final @Observes StartupEvent event) {
			
		log.info("Migrating database");
		//flyway.migrate();
		//log.info(flyway.info().current().getVersion().toString());
	}
}
