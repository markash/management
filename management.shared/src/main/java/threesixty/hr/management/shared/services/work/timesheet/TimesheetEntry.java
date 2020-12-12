package threesixty.hr.management.shared.services.work.timesheet;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

public class TimesheetEntry implements Serializable {
	private static final long serialVersionUID = 6865859007849830639L;

	private static final ZoneId UTC = ZoneId.of("Z");
			
	private Long id;
	private Long personId;
	private LocalDateTime activityStart;
	private LocalDateTime activityEnd;
	private LocalTime duration;
	
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	
	public Long getPersonId() { return personId; }
	public void setPersonId(Long personId) { this.personId = personId; }
	
	public LocalDateTime getActivityStart() { return activityStart; }
	public void setActivityStart(LocalDateTime start) { this.activityStart = start; }
	
	public LocalDateTime getActivityEnd() { return activityEnd; }
	public void setActivityEnd(LocalDateTime end) { this.activityEnd = end; }
	
	public LocalTime getDuration() { return duration; }
	public void setDuration(LocalTime duration) { this.duration = duration; }
}
