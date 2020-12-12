package threesixty.hr.management.shared.services.rest;

import java.util.List;

public interface IBaseRestResourceClient<T> {

	/**
	 * Retrieve all the entries from the rest resource
	 * @return List of entries
	 */
	List<T> retrieveAll();
}
