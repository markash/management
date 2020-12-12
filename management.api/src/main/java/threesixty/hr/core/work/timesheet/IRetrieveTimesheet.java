package threesixty.hr.core.work.timesheet;

import java.util.List;

import threesixty.hr.core.date.DateRange;

public interface IRetrieveTimesheet {
    /**
     * Retrieve the timesheet entries for a submitter
     * @param submitterId The identifier of the submitter
     * @param dateRange The date range of the timesheet entries
     * @return The list of timesheets
     */
    List<Timesheet> retrieveBySubmitter(
        final Long submitterId,
        final DateRange dateRange);
}
