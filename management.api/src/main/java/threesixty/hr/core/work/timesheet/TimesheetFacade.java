package threesixty.hr.core.work.timesheet;

import java.util.List;
import java.util.function.Supplier;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import threesixty.hr.core.date.DateRange;
import threesixty.hr.infrastructure.work.timesheet.PanacheTimesheetRepository;

@ApplicationScoped
public class TimesheetFacade implements IRetrieveTimesheet, IPersistTimesheet {

    @Inject
    PanacheTimesheetRepository timesheetRepository;

    @Override
    public List<Timesheet> retrieveBySubmitter(
        final Long submitterId,
        final DateRange dateRange) {
        
        return timesheetRepository.retrieveBySubmitter(submitterId, dateRange);
    }
    
    @Override
    public Timesheet create(
        final Timesheet timesheet) {
        
        TimesheetCreationVerifier verifier = new TimesheetCreationVerifier();

        verifier.verifyNotNull(timesheet);
        verifier.verifySubmitter(timesheet);
        verifier.verifyActivityDuration(timesheet);

        if (timesheet.hasActivityDateRange()) {

            DateRange dateRange = timesheet.getActivityDateRange();
            Supplier<List<Timesheet>> timesheetSupplier = () -> retrieveBySubmitter(timesheet.getSubmitter().getPartyId(), dateRange);
            
            verifier.verifyActivityDoesNotOverlap(dateRange, timesheetSupplier);           
        }
        
        timesheetRepository.persist(timesheet);

        return timesheet;
    }

    
}
