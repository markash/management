package threesixty.hr.management.shared.services.work.order;

import java.time.LocalDateTime;
import java.util.Optional;

import threesixty.hr.management.shared.services.EffectiveDateEntity;

public class WorkOrderRole implements EffectiveDateEntity {

	private Long id;
	private WorkOrderParty assignedTo;
	private String role;
	private String roleDescription;
	private LocalDateTime effectiveFrom;
	private LocalDateTime effectiveTo;
	
	public Long getId() { return id; }
	public void setId(final Long id) { this.id = id; }
	
	public WorkOrderParty getAssignedTo() { return assignedTo; }
	public void setAssignedTo(final WorkOrderParty assignedTo) { this.assignedTo = assignedTo; }
	
	public String getRole() { return role; }
	public void setRole(String role) { this.role = role; }
	
	public void setRoleDescription(String roleDescription) { this.roleDescription = roleDescription; }
	public String getRoleDescription() { return roleDescription; }
	
	@Override
	public LocalDateTime getEffectiveFrom() { return effectiveFrom; }
	@Override
	public void setEffectiveFrom(LocalDateTime effectiveFrom) { this.effectiveFrom = effectiveFrom; }
	
	@Override
	public LocalDateTime getEffectiveTo() { return effectiveTo; }
	@Override
	public void setEffectiveTo(LocalDateTime effectiveTo) { this.effectiveTo = effectiveTo; }
	
	/**
	 * Returns whether the work order role is assigned to the party id
	 * @param partyId The party id
	 * @return True if assigned else false
	 */
	public boolean isAssignedTo(final Long partyId) {
		
		return Optional.ofNullable(getAssignedTo())
				.map(WorkOrderParty::getId)
				.map(id -> id != null ? id : -99999L)
				.map(id -> id.equals(partyId))
				.orElse(false);
	}
}
