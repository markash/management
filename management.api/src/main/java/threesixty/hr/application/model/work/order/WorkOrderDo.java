package threesixty.hr.application.model.work.order;

import java.time.LocalDateTime;
import java.util.List;

public class WorkOrderDo extends WorkOrderBaseDo {

	public LocalDateTime scheduledStart;
	public LocalDateTime scheduledEnd;
	public LocalDateTime actualStart;
	public LocalDateTime actualEnd;
	public WorkOrderTypeDo type;
	public List<WorkOrderRoleDo> roles;
}
