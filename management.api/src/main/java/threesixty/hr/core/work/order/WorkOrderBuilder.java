package threesixty.hr.core.work.order;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class WorkOrderBuilder {

	final WorkOrder instance = new WorkOrder();
	
	private WorkOrderBuilder() { }
	
	public static WorkOrderBuilder builder() {
		
		return new WorkOrderBuilder();
	}
	
	public WorkOrderBuilder withId(final Long id) {
		
		this.instance.setId(id);
		return this;
	}
	
	public WorkOrderBuilder withName(final String name) {
		
		this.instance.setName(name);
		return this;
	}

	public WorkOrderBuilder withScheduledStart(final LocalDateTime scheduledStart) {
		
		this.instance.setScheduledStart(scheduledStart);
		return this;
	}

	public WorkOrderBuilder withScheduledEnd(final LocalDateTime scheduledEnd) {
		
		this.instance.setScheduledEnd(scheduledEnd);
		return this;
	}

	public WorkOrderBuilder withActualStart(final LocalDateTime actualStart) {
		
		this.instance.setActualStart(actualStart);
		return this;
	}

	public WorkOrderBuilder withActualEnd(final LocalDateTime actualEnd) {
		
		this.instance.setActualEnd(actualEnd);
		return this;
	}
	
	public WorkOrderBuilder withRoles(WorkOrderRole...roles) {
		
		Set<WorkOrderRole> workOrderRoles =
				Optional.ofNullable(roles)
					.map(x -> Arrays.stream(x).collect(Collectors.toSet()))
					.orElse(null);
		
		this.instance.setRoles(workOrderRoles);
		return this;
	}
	
	public WorkOrderBuilder withType(final WorkOrderType type) {
		
		this.instance.setType(type);
		return this;
	}
	
	public WorkOrderBuilder withProcessed(final LocalDateTime processed) {
		
		this.instance.setProcessed(processed);
		return this;
	}
	
	public WorkOrderTypeBuilder type() {
		
		return WorkOrderTypeBuilder.builder(this);
	}
	
	public WorkOrderRoleBuilder role() {
		
		return WorkOrderRoleBuilder.builder(this);
	}
	
	public WorkOrder build() {
		
		return this.instance;
	}
}
