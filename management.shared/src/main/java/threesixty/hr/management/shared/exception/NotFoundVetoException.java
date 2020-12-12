package threesixty.hr.management.shared.exception;

import org.eclipse.scout.rt.platform.exception.IProcessingStatus;
import org.eclipse.scout.rt.platform.exception.VetoException;

public class NotFoundVetoException extends VetoException {
	private static final long serialVersionUID = 8630702733983960993L;

	public NotFoundVetoException() {
		super();
	}

	public NotFoundVetoException(
			final IProcessingStatus status) {
		
		super(status);
	}

	public NotFoundVetoException(
			final String message, 
			final Object... args) {
		
		super(message, args);
	}
}
