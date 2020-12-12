package threesixty.hr.management.shared.services.employee;

import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

import threesixty.hr.management.shared.employee.EmployeeTablePageData;
import threesixty.hr.management.shared.employee.EmployeeTablePageData.EmployeeTableRowData;

public class EmployeesToPageMapper implements Function<Collection<Person>, EmployeeTablePageData> {

	@Override
	public EmployeeTablePageData apply(
			final Collection<Person> persons) {
		
		EmployeeTablePageData pageData = new EmployeeTablePageData();
		
		EmployeeToRowMapper mapper = new EmployeeToRowMapper();
		
		EmployeeTableRowData[] rows = 
			persons.stream()
				.map(mapper)
				.flatMap(l -> l.stream())
				.collect(Collectors.toList())
				.toArray(new EmployeeTableRowData[0]);
		
		pageData.setRows(rows);
		
		return pageData;
	}
}
