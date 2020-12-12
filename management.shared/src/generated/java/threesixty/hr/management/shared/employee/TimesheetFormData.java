package threesixty.hr.management.shared.employee;

import javax.annotation.Generated;

import org.eclipse.scout.rt.shared.data.form.AbstractFormData;
import org.eclipse.scout.rt.shared.data.form.fields.AbstractValueFieldData;

/**
 * <b>NOTE:</b><br>
 * This class is auto generated by the Scout SDK. No manual modifications
 * recommended.
 */
@Generated(value = "threesixty.hr.management.client.employee.TimesheetForm", comments = "This class is auto generated by the Scout SDK. No manual modifications recommended.")
public class TimesheetFormData extends AbstractFormData {
	private static final long serialVersionUID = 1L;

	public Date getDate() {
		return getFieldByClass(Date.class);
	}

	public Duration getDuration() {
		return getFieldByClass(Duration.class);
	}

	public EndTime getEndTime() {
		return getFieldByClass(EndTime.class);
	}

	public Id getId() {
		return getFieldByClass(Id.class);
	}

	public Person getPerson() {
		return getFieldByClass(Person.class);
	}

	public Project getProject() {
		return getFieldByClass(Project.class);
	}

	public StartTime getStartTime() {
		return getFieldByClass(StartTime.class);
	}

	public Task getTask() {
		return getFieldByClass(Task.class);
	}

	public TimeModeSelector getTimeModeSelector() {
		return getFieldByClass(TimeModeSelector.class);
	}

	public static class Date extends AbstractValueFieldData<java.util.Date> {
		private static final long serialVersionUID = 1L;
	}

	public static class Duration extends AbstractValueFieldData<java.util.Date> {
		private static final long serialVersionUID = 1L;
	}

	public static class EndTime extends AbstractValueFieldData<java.util.Date> {
		private static final long serialVersionUID = 1L;
	}

	public static class Id extends AbstractValueFieldData<Long> {
		private static final long serialVersionUID = 1L;
	}

	public static class Person extends AbstractValueFieldData<Long> {
		private static final long serialVersionUID = 1L;
	}

	public static class Project extends AbstractValueFieldData<Long> {
		private static final long serialVersionUID = 1L;
	}

	public static class StartTime extends AbstractValueFieldData<java.util.Date> {
		private static final long serialVersionUID = 1L;
	}

	public static class Task extends AbstractValueFieldData<Long> {
		private static final long serialVersionUID = 1L;
	}

	public static class TimeModeSelector extends AbstractValueFieldData<Long> {
		private static final long serialVersionUID = 1L;
	}
}