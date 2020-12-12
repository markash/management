package threesixty.hr.core.work.order;

import java.util.List;

public interface IRetrieveWorkOrderTypes {

	/**
	 * Retrieves all the work order types
	 * @return The list of work order types
	 */
	List<WorkOrderType> retrieveAll();
}
