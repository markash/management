package threesixty.hr.management.shared.services.employee;

import java.util.List;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;

import threesixty.hr.management.shared.employee.EmployeeTablePageData;
import threesixty.hr.management.shared.employee.PersonTabFormData;

@TunnelToServer
public interface IEmployeeManager extends IService {
	EmployeeTablePageData retrieveTableData(
			final SearchFilter filter);
	
	PersonTabFormData retrieve(
			final PersonTabFormData formData);
	
	List<threesixty.hr.management.shared.services.party.person.Person> lookup(
			final ILookupCall<Long> call);
}
