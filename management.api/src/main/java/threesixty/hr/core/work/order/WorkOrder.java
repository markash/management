package threesixty.hr.core.work.order;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import threesixty.hr.core.DirectNamedEntity;
import threesixty.hr.core.comparison.EntityComparison;
import threesixty.hr.core.comparison.PropertyComparisonException;
import threesixty.hr.core.comparison.ValueChanges;

@Entity
@Table(name = "WORK_ORDER")
public class WorkOrder extends PanacheEntityBase implements DirectNamedEntity {

	public static final String FIELD_ID 				= "id";
	public static final String FIELD_NAME 				= "name";
	public static final String FIELD_SCHEDULED_START 	= "scheduledStart";
	public static final String FIELD_SCHEDULED_END 		= "scheduledEnd";
	public static final String FIELD_ACTUAL_START 		= "actualStart";
	public static final String FIELD_ACTUAL_END 		= "actualEnd";
	public static final String FIELD_ROLES 				= "roles";
	public static final String FIELD_TYPE 				= "type";

	@Id @Column(name = "WORK_ORDER_ID")
	private Long id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "SCHEDULED_START")
	private LocalDateTime scheduledStart;
	
	@Column(name = "SCHEDULED_END")
	private LocalDateTime scheduledEnd;
	
	@Column(name = "ACTUAL_START")
	private LocalDateTime actualStart;
	
	@Column(name = "ACTUAL_END")
	private LocalDateTime actualEnd;
	
	@OneToMany(mappedBy = "workOrderId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER) 
	private Set<WorkOrderRole> roles = new HashSet<WorkOrderRole>();
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "WORK_ORDER_TYPE")
	private WorkOrderType type;
	
	@Column(name = "PROCESSED")
	private LocalDateTime processed;
	
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	
	@Override
	public String getName() { return this.name; }
	@Override
	public void setName(final String name) { this.name = name; }
	
	public LocalDateTime getScheduledStart() { return scheduledStart; }
	public void setScheduledStart(LocalDateTime scheduledStart) { this.scheduledStart = scheduledStart; }
	
	public LocalDateTime getScheduledEnd() { return scheduledEnd; }
	public void setScheduledEnd(LocalDateTime scheduledEnd) { this.scheduledEnd = scheduledEnd; }
	
	public LocalDateTime getActualStart() { return actualStart; }
	public void setActualStart(LocalDateTime actualStart) { this.actualStart = actualStart; }
	
	public LocalDateTime getActualEnd() { return actualEnd; }
	public void setActualEnd(LocalDateTime actualEnd) { this.actualEnd = actualEnd; }
	
	public Set<WorkOrderRole> getRoles() { return roles; }
	public void setRoles(Set<WorkOrderRole> roles) { this.roles = roles; }
	
	public WorkOrderType getType() { return type; }
	public void setType(WorkOrderType type) { this.type = type; }
	
	public ValueChanges compareWith(final WorkOrder other) throws PropertyComparisonException {
		
		return 
			EntityComparison.of(
				WorkOrder.class, 
				FIELD_ID,
				FIELD_NAME,
				FIELD_SCHEDULED_START,
				FIELD_SCHEDULED_END,
				FIELD_ACTUAL_START,
				FIELD_ACTUAL_END,
				FIELD_ROLES,
				FIELD_TYPE
			).compare(this, other);
	}
	
	
	@Override
	public LocalDateTime getProcessed() { return this.processed; }
	@Override
	public void setProcessed(LocalDateTime processed) { this.processed = processed; }
}
