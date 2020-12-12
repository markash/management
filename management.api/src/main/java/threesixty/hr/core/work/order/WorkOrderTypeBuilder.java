package threesixty.hr.core.work.order;

import java.time.LocalDateTime;

public class WorkOrderTypeBuilder {

	private final WorkOrderBuilder parent;
	private final WorkOrderType instance = new WorkOrderType();
	
	private WorkOrderTypeBuilder(final WorkOrderBuilder parent) {
		
		this.parent = parent;
	}
	
	public static WorkOrderTypeBuilder builder(final WorkOrderBuilder parent) {
		
		return new WorkOrderTypeBuilder(parent);
	}
	
	public WorkOrderTypeBuilder withName(final String name) {
		
		this.instance.setName(name);
		return this;
	}

	public WorkOrderTypeBuilder withDescription(final String description) {
		
		this.instance.setDescription(description);
		return this;
	}

	public WorkOrderTypeBuilder withEstimate(final Boolean estimate) {
		
		this.instance.setEstimate(estimate);
		return this;
	}
	
	public WorkOrderTypeBuilder withCapitalize(final Boolean capitalize) {
		
		this.instance.setCapitalize(capitalize);
		return this;
	}
	
	public WorkOrderTypeBuilder withProcessed(final LocalDateTime processed) {
		
		this.instance.setProcessed(processed);
		return this;
	}
	
	public WorkOrderBuilder end() {
		
		this.parent.instance.setType(this.instance);
		return this.parent;
	}
}
