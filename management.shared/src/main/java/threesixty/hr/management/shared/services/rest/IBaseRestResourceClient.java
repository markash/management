package threesixty.hr.management.shared.services.rest;

import java.util.List;

public interface IBaseRestResourceClient<T, ID> {

	/**
	 * Retrieve all the entries from the rest resource
	 * @return List of entries
	 */
	List<T> retrieveAll();
	
	/**
	 * Retrieve the resource by id
	 * @return The resource identified by the id or null
	 */
	T retrieveById(final ID id);
}
