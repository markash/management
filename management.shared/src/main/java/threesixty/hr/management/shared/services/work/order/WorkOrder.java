package threesixty.hr.management.shared.services.work.order;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import threesixty.hr.management.shared.services.DirectNamedEntity;

public class WorkOrder implements DirectNamedEntity {

	private Long id;
	private String name;
	private LocalDateTime scheduledStart;
	private LocalDateTime scheduledEnd;
	private LocalDateTime actualStart;
	private LocalDateTime actualEnd;
	private List<WorkOrderRole> roles = new ArrayList<>();
	private WorkOrderType type;
	
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	
	public LocalDateTime getScheduledStart() { return scheduledStart; }
	public void setScheduledStart(LocalDateTime scheduledStart) { this.scheduledStart = scheduledStart; }
	
	public LocalDateTime getScheduledEnd() { return scheduledEnd; }
	public void setScheduledEnd(LocalDateTime scheduledEnd) { this.scheduledEnd = scheduledEnd; }
	
	public LocalDateTime getActualStart() { return actualStart; }
	public void setActualStart(LocalDateTime actualStart) { this.actualStart = actualStart; }
	
	public LocalDateTime getActualEnd() { return actualEnd; }
	public void setActualEnd(LocalDateTime actualEnd) { this.actualEnd = actualEnd; }
	
	public Boolean getEstimate() { return Optional.ofNullable(this.type).map(WorkOrderType::isEstimate).orElse(false); }
	public Boolean getCapitalize() { return Optional.ofNullable(this.type).map(WorkOrderType::isCapitalize).orElse(false); }
	
	public Date getScheduledStartDate() { return toDate(getScheduledStart()); }
	public Date getScheduledEndDate() { return toDate(getScheduledEnd()); }
	public Date getActualStartDate() { return toDate(getActualStart()); }
	public Date getActualEndDate() { return toDate(getActualEnd()); }
	
	public List<WorkOrderRole> getRoles() { return roles; }
	public void setRoles(List<WorkOrderRole> roles) { this.roles = roles; }
	
	@Override
	public String getName() { return this.name; }
	@Override
	public void setName(final String name) { this.name = name; }

	public WorkOrderType getType() { return type; }
	public void setType(WorkOrderType type) { this.type = type; }
	
	/**
	 * Returns whether the specific partyId is assigned to the work order
	 * @param partyId The party id
	 * @return True is assigned else false
	 */
	public boolean isAssignedTo(
			final Long partyId) {
		
		return Optional.ofNullable(getRoles()).orElse(new ArrayList<>())
				.stream()
				.anyMatch(workOrderRole -> workOrderRole.isAssignedTo(partyId));
	}
	
	
	private Date toDate(
			final LocalDateTime date) {
		
		return Optional.ofNullable(date)
				.map(d -> Date.from(d.atZone(ZoneId.systemDefault()).toInstant()))
				.orElse(null);
	}
}
