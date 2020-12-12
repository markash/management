package threesixty.hr.core.work.order;

import threesixty.hr.core.exception.EntityModifyException;

public interface IModifyWorkOrder {
	/**
	 * Modify the work order
	 * @param workOrder The work order that contains an existing work order identifier and the updated values.
	 * @return The modified work order
	 * @throws EntityModifyException If there is an exception modifying the entity
	 */
	WorkOrder modify(
			final WorkOrder workOrder) throws EntityModifyException;
}
