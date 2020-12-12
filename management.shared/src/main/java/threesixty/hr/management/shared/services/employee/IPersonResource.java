package threesixty.hr.management.shared.services.employee;

import java.util.List;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

@TunnelToServer
public interface IPersonResource extends IService {
	
	/**
	 * Retrieve the employees
	 * @return List of employees
	 */
	List<Person> retrieve(
			final SearchFilter filter);
}
