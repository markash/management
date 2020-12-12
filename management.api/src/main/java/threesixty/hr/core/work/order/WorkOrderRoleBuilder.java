package threesixty.hr.core.work.order;

import java.time.LocalDateTime;

import threesixty.hr.core.party.Party;

public class WorkOrderRoleBuilder {

	private final WorkOrderBuilder parent;
	private final WorkOrderRole instance = new WorkOrderRole();
	
	private WorkOrderRoleBuilder(final WorkOrderBuilder parent) {
		
		this.parent = parent;
		this.instance.setWorkOrderId(parent.instance.getId());
	}
	
	public static WorkOrderRoleBuilder builder(final WorkOrderBuilder parent) {
		
		return new WorkOrderRoleBuilder(parent);
	}
	
	public WorkOrderRoleBuilder withAssignedTo(final Party assignedTo) {
		
		this.instance.setAssignedTo(assignedTo);
		return this;
	}

	public WorkOrderRoleBuilder withRoleType(final WorkOrderRoleType roleType) {
		
		this.instance.setRoleType(roleType);
		return this;
	}

	public WorkOrderRoleBuilder withEffectiveFrom(final LocalDateTime effectiveFrom) {
		
		this.instance.setEffectiveFrom(effectiveFrom);
		return this;
	}

	public WorkOrderRoleBuilder withEffectiveTo(final LocalDateTime effectiveTo) {
		
		this.instance.setEffectiveTo(effectiveTo);
		return this;
	}

	public WorkOrderRoleBuilder withProcessed(final LocalDateTime processed) {
		
		this.instance.setProcessed(processed);
		return this;
	}
	
	public WorkOrderBuilder end() {
		
		this.parent.instance.getRoles().add(this.instance);
		return this.parent;
	}
}
