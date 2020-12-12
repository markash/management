package threesixty.hr.core.work.timesheet;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import threesixty.hr.core.EntityPersistVerifier;
import threesixty.hr.core.date.DateRange;
import threesixty.hr.core.exception.EntityPersistException;
import threesixty.hr.infrastructure.work.timesheet.PanacheTimesheetRepository;

public class TimesheetCreationVerifier implements EntityPersistVerifier {
    
    public void verifySubmitter(
        final Timesheet timesheet) {

        verifyNotNull(timesheet.getSubmitter(), "The timesheet submitter is required");
        verifyNotNull(timesheet.getSubmitter().getPartyId(), "The timesheet submitter is required");
    }

    public void verifyActivityDuration(
        final Timesheet timesheet) {

        if (Objects.isNull(timesheet.getActivityDuration()) &&
            Objects.isNull(timesheet.getActivityStart()) &&
            Objects.isNull(timesheet.getActivityEnd())) {

            throw new EntityPersistException("The timesheet duration or activity start & end must be provided");
        }

        if (Objects.nonNull(timesheet.getActivityStart()) && Objects.isNull(timesheet.getActivityEnd())) {

            throw new EntityPersistException("The timesheet activity end must be given when the start is provided");
        }

        if (Objects.isNull(timesheet.getActivityStart()) && Objects.nonNull(timesheet.getActivityEnd())) {

            throw new EntityPersistException("The timesheet activity start must be given when the end is provided");
        }
    }
    
    public void verifyActivityDoesNotOverlap(
        final DateRange dateRange, 
        final Supplier<List<Timesheet>> timesheetSupplier) {

        boolean timesheetOverlaps = 
            timesheetSupplier.get().stream()
                .filter(Timesheet::hasActivityDateRange)
                .map(Timesheet::getActivityDateRange)
                .collect(Collectors.toList())
                .stream()
                .anyMatch(timesheetDateRange -> timesheetDateRange.overlapsExclusive(dateRange));

        if (timesheetOverlaps) {

            throw new EntityPersistException("The timesheet activity start & end time overlaps an existing timesheet");
        }
    }
}
