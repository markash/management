package threesixty.hr.management.shared.services.employee;

import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.platform.text.TEXTS;

public class PartyRelationshipIncompleteVetoException extends VetoException {
	private static final long serialVersionUID = -5715047433703426774L;

	public PartyRelationshipIncompleteVetoException() {
		super(TEXTS.get("PartyRelationshipIncompleteException"));
	}
}
