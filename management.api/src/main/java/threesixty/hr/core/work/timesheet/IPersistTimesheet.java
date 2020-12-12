package threesixty.hr.core.work.timesheet;

public interface IPersistTimesheet {
    /**
     * Create the timesheet
     * @param timesheet The time to create
     * @return The created timesheet
     */
    Timesheet create(final Timesheet timesheet);
}
