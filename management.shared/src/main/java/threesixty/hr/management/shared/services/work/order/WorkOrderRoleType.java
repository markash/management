package threesixty.hr.management.shared.services.work.order;

import threesixty.hr.management.shared.services.TypeEntity;

/**
 * 
 * @author Mark Ashworth
 */
public class WorkOrderRoleType implements TypeEntity {

	private String name;
	private String description;
	
	/**
	 * The name of the entity
	 * @return The name of the entity
	 */
	@Override
	public String getName() { return name; }
	@Override
	public void setName(String name) { this.name = name; }
	
	@Override
	public String getDescription() { return description; }
	@Override
	public void setDescription(String description) { this.description = description; }
}
