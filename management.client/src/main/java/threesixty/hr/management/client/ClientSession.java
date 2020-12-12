package threesixty.hr.management.client;

import java.util.Locale;

import org.eclipse.scout.rt.client.AbstractClientSession;
import org.eclipse.scout.rt.client.IClientSession;
import org.eclipse.scout.rt.client.session.ClientSessionProvider;
import org.eclipse.scout.rt.client.ui.ClientUIPreferences;
import org.eclipse.scout.rt.shared.services.common.code.CODES;

/**
 * @author USER
 */
public class ClientSession extends AbstractClientSession {

	public ClientSession() {
		super(true);
	}

	/**
	 * @return The {@link IClientSession} which is associated with the current
	 *         thread, or {@code null} if not found.
	 */
	public static ClientSession get() {
		return ClientSessionProvider.currentSession(ClientSession.class);
	}

	@Override
	protected void execLoadSession() {
		CODES.getAllCodeTypes("threesixty.hr.management.shared");

		configureUserLocale();
	    
		setDesktop(new Desktop());
	}
	
	private void configureUserLocale() {
		
	    String localeString = 
	    		ClientUIPreferences.getClientPreferences(get())
	    			.get(ClientPreferences.PREF_USER_LOCALE.name(), null);
	    
	    if (localeString != null) {
	    	setLocale(Locale.forLanguageTag(localeString));
	    }
	}
}
