package threesixty.hr.application.model.work.timesheet;

import java.time.LocalDateTime;

import threesixty.hr.application.model.work.order.WorkOrderBaseDo;

public class TimesheetDo {

	public Long id;
	public WorkOrderBaseDo workOrder;
	//public Activity activity;
	public LocalDateTime activityStart;
	public LocalDateTime activityEnd;
	public Integer activityDuration;
	public String comment;
}
