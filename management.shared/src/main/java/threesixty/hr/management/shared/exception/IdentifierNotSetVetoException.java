package threesixty.hr.management.shared.exception;

import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.platform.text.TEXTS;

/**
 * Identifier not set veto exception is thrown by the server code
 * when expecting the id of an entity to be set so that it can be retrieved.
 * 
 * @author Mark Ashworth
 */
public class IdentifierNotSetVetoException extends VetoException {
	private static final long serialVersionUID = 746571321820226443L;

	/**
	 * Constructs the veto exception with the entity name
	 * @param entityName The name of the entity, i.e. Work Order, Party or Employee
	 */
	public IdentifierNotSetVetoException(
			final String entityName) {
		
		super(TEXTS.get("IdNotSetVetoException", entityName));
	}	
}
