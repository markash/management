package threesixty.hr.management.shared.services.employee;

public class EmploymentRelationship extends PartyRelationship {

	public EmploymentRelationship() {
		super(new Organization(), new Person());
	}
}
