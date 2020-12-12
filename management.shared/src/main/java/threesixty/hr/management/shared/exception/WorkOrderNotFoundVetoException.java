package threesixty.hr.management.shared.exception;

import java.util.Optional;

import org.eclipse.scout.rt.platform.text.TEXTS;

public class WorkOrderNotFoundVetoException extends NotFoundVetoException {
	private static final long serialVersionUID = -3364911887713580662L;

	
	public WorkOrderNotFoundVetoException(
			final Long id) {
		
		super(TEXTS.get(
				"IdNotFoundVetoException", 
				Optional.ofNullable(id).map(Object::toString).orElse("null"), 
				"Work Order"));
	}
}
