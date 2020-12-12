package threesixty.hr.management.shared.text;

import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.AbstractDynamicNlsTextProviderService;

/**
 * @author USER
 */
@Order(-2000)
public class DefaultTextProviderService extends AbstractDynamicNlsTextProviderService {
	@Override
	public String getDynamicNlsBaseName() {
		return "threesixty.hr.management.shared.texts.Texts";
	}
}
