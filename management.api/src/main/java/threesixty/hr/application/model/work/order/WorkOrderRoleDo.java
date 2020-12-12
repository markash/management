package threesixty.hr.application.model.work.order;

import java.time.LocalDateTime;

public class WorkOrderRoleDo {

	public WorkOrderPartyDo assignedTo; 
	public String role;
	public String roleDescription;
	public LocalDateTime effectiveFrom;
	public LocalDateTime effectiveTo;
}
