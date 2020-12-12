package threesixty.hr.infrastructure.work.timesheet;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import threesixty.hr.core.date.DateRange;
import threesixty.hr.core.work.timesheet.Timesheet;

@ApplicationScoped
public class PanacheTimesheetRepository implements PanacheRepository<Timesheet> {
    
    /**
     * List the timesheet entries by submitter
     * @param submitterId The identifier of the submitter
     * @param dateRange The from and to date which to retrieve
     * @return The list of timesheet entries
     */
    public List<Timesheet> retrieveBySubmitter(
        final Long submitterId,
        final DateRange dateRange) {

            final String query = 
                "submitter.partyId = :partyId " + 
            "AND activityStart    >= :from " +
            "AND activityEnd      <= :to";

        final Parameters parameters = 
                Parameters
                    .with("partyId", submitterId)
                    .and("from", dateRange.getFrom())
                    .and("to", dateRange.getTo());

        return Timesheet.list(query, parameters);
    }
}
