package threesixty.hr.core.work.order;

import java.util.List;

public interface IRetrieveWorkOrder {

	/**
	 * Retrieve the work order by the unique identifier
	 * @return The work order
	 */
	WorkOrder retrieveById(final Long id);
	
	/**
	 * Retrieve all the work orders
	 * @return The list of work orders
	 */
	List<WorkOrder> retrieveAll();
}
