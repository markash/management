package threesixty.hr.management.server.services.employee;

import java.util.List;

import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;

import threesixty.hr.management.server.services.party.person.PersonResourceClient;
import threesixty.hr.management.shared.employee.EmployeeTablePageData;
import threesixty.hr.management.shared.employee.PersonTabFormData;
import threesixty.hr.management.shared.services.employee.IEmployeeManager;
import threesixty.hr.management.shared.services.party.Party;
import threesixty.hr.management.shared.services.party.PersonsToEmployeePageMapper;
import threesixty.hr.management.shared.services.party.person.Person;

public class EmployeeManager implements IEmployeeManager {
	
	public List<Person> retrieve(
			final SearchFilter filter) {
		
		return BEANS.get(PersonResourceClient.class).retrieveAll();
	}
	
	@Override
	public EmployeeTablePageData retrieveTableData(
			final SearchFilter filter) {
	
		return new PersonsToEmployeePageMapper().apply(retrieve(filter));
	}
	
	@Override
	public PersonTabFormData retrieve(
			final PersonTabFormData formData) {
		
		if (!formData.getId().isValueSet()) {
			throw new VetoException("The person id is not set");
		}
		
		Party party = 
				BEANS.get(PersonResourceClient.class)
					.retrieveById(formData.getId().getValue());
		
		if (party != null) {
			formData.getName().setValue(party.name);
		}
		
		return formData;
	}
	
	@Override
	public List<Person> lookup(
			final ILookupCall<Long> call) {
		
		SearchFilter filter = new SearchFilter();
		
		return retrieve(filter);
	}
}
