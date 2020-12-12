package threesixty.hr.management.shared.services.employee;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import threesixty.hr.management.shared.employee.EmployeeTablePageData.EmployeeTableRowData;

public class EmployeeToRowMapper implements Function<Person, List<EmployeeTableRowData>> {

	@Override
	public List<EmployeeTableRowData> apply(
			final Person person) {
		
		return person.getRelationships(RelationshipType.EMPLOYMENT)
				.stream()
				.map(relationship -> map(person, relationship))
				.collect(Collectors.toList());
	}
	
	private EmployeeTableRowData map(
			final Person person, 
			final PartyRelationship relationship) {
		
		EmployeeTableRowData rowData = new EmployeeTableRowData();
		
		rowData.setName(person.getName());
		rowData.setFirstName(person.getFirstName());
		rowData.setLastName(person.getLastName());
		rowData.setIdentityNumber(person.getIdentityNumber());
		rowData.setDateOfBirth(person.getDateOfBirth());
		rowData.setEmployeeId(person.getId().longValue());
		
		return rowData;
	}
}
