package threesixty.hr.management.shared.services.work.timesheet;

import java.time.LocalDateTime;

public class Timesheet {

	private Long id;
	private LocalDateTime activityStart;
	private LocalDateTime activityEnd;
	private String comment;
	
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	
	public LocalDateTime getActivityStart() { return activityStart; }
	public void setActivityStart(LocalDateTime activityStart) { this.activityStart = activityStart; }
	
	public LocalDateTime getActivityEnd() { return activityEnd; }
	public void setActivityEnd(LocalDateTime activityEnd) { this.activityEnd = activityEnd; }
	
	public String getComment() { return comment; }
	public void setComment(String comment) { this.comment = comment; }
}
