package threesixty.hr.management.shared.services.work.order;

import threesixty.hr.management.shared.services.DirectNamedEntity;

public class WorkOrderParty implements DirectNamedEntity {

	private Long id;
	private String name;

	public Long getId() { return id; }
	public void setId(final Long id) { this.id = id; }
	
	@Override
	public String getName() { return this.name; }
	@Override
	public void setName(final String name) { this.name = name; }
}
