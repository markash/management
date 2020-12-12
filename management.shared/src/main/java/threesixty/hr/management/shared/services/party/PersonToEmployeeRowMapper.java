package threesixty.hr.management.shared.services.party;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import threesixty.hr.management.shared.employee.EmployeeTablePageData.EmployeeTableRowData;
import threesixty.hr.management.shared.services.party.person.Person;

public class PersonToEmployeeRowMapper implements Function<Person, List<EmployeeTableRowData>> {

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
		
		rowData.setName(person.name);
		rowData.setFirstName(person.givenName);
		rowData.setLastName(person.familyName);
		//rowData.setIdentityNumber(person.getIdentityNumber());
		//rowData.setDateOfBirth(person.getDateOfBirth());
		rowData.setEmployeeId(person.id);
		
		Optional<Party> employer = Optional.ofNullable(person.getEmployer());
		
		if (employer.isPresent()) {
		
			rowData.setOrganizationName(employer.get().name);
		}
		
		return rowData;
	}
	
}
