package threesixty.hr.management.server.services.db;

import javax.security.auth.Subject;

import org.eclipse.scout.rt.platform.Bean;
import org.eclipse.scout.rt.platform.IPlatform.State;
import org.eclipse.scout.rt.platform.IPlatformListener;
import org.eclipse.scout.rt.platform.PlatformEvent;
import org.eclipse.scout.rt.platform.exception.PlatformException;
import org.eclipse.scout.rt.platform.security.SimplePrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Bean
public class DatabaseMigrationService implements IPlatformListener {

	private static final Logger LOG = LoggerFactory.getLogger(DatabaseMigrationService.class);
	
	public static Subject s_subject;

	static {
		s_subject = new Subject();
		s_subject.getPrincipals().add(new SimplePrincipal("bahbah"));
		s_subject.setReadOnly();
	}

	@Override
	public void stateChanged(
			final PlatformEvent event) throws PlatformException {
		
		if (event.getState() == State.PlatformStarted) {
			
//			try {
//				ServerRunContext runContext = ServerRunContexts.empty();
//				runContext.withSubject(s_subject);
//				runContext.withSession(BEANS.get(ServerSessionProviderWithCache.class).provide(runContext.copy()));
//				runContext.run(() -> BEANS.get(IDbSetupService.class).migrate(), DefaultExceptionTranslator.class);
//			} catch (Exception e) {
//				throw new PlatformException("Unable to start server application.", e);
//			}

			LOG.info("bahbah server initialized");
		}
	}
	
	public static Subject getSubject() {
		return s_subject;
	}
}
