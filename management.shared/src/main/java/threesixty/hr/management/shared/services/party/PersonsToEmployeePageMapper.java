package threesixty.hr.management.shared.services.party;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import threesixty.hr.management.shared.employee.EmployeeTablePageData;
import threesixty.hr.management.shared.employee.EmployeeTablePageData.EmployeeTableRowData;
import threesixty.hr.management.shared.services.party.person.Person;

public class PersonsToEmployeePageMapper implements Function<List<Person>, EmployeeTablePageData> {

	@Override
	public EmployeeTablePageData apply(
			final List<Person> results) {
		
		EmployeeTablePageData pageData = new EmployeeTablePageData();
		
		PersonToEmployeeRowMapper mapper = new PersonToEmployeeRowMapper();
		
		EmployeeTableRowData[] rows = 
			results.stream()
				.map(mapper)
				.flatMap(l -> l.stream())
				.collect(Collectors.toList())
				.toArray(new EmployeeTableRowData[0]);
		
		pageData.setRows(rows);
		
		return pageData;
	}
}
