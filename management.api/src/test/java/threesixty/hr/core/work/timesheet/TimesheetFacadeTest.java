package threesixty.hr.core.work.timesheet;

import java.time.LocalDateTime;
import java.util.List;

import javax.inject.Inject;

import com.google.common.truth.Truth;

import org.junit.jupiter.api.Test;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import threesixty.hr.core.date.DateRange;
import threesixty.hr.core.date.DateRangeUtility;
import threesixty.hr.core.exception.EntityPersistException;
import threesixty.hr.core.party.Person;
import threesixty.hr.infrastructure.party.PanachePersonRepository;

@QuarkusTest
class TimesheetFacadeTest {

    @Inject
    IRetrieveTimesheet retrieve;

    @Inject
    IPersistTimesheet create;

    @Inject
    PanachePersonRepository personRepository;

    @Test
    @TestTransaction
    void retrieveForSubmitter() {

        DateRange juneMonth = 
            DateRange.Builder
                .fromStartOfMonth("2020-06-10")
                .toEndOfMonth("2020-06-10")
                .build();

        List<Timesheet> results = retrieve.retrieveBySubmitter(8L, juneMonth);

        Truth.assertThat(results).isNotNull();
        Truth.assertThat(results).isNotEmpty();
    }

    @Test
    @TestTransaction
    void createTimesheet() {

        Person submitter = 
            personRepository.findByFullName("M%", "Ashworth")
                .get(0);

        Timesheet timesheet = 
            Timesheet.builder()
                .submitter(submitter)
                .activityStart(LocalDateTime.parse("2020-10-01T08:00:00"))
                .activityEnd(LocalDateTime.parse("2020-10-01T08:30:00"))
                .build();

        create.create(timesheet);

        Truth.assertThat(timesheet.getId()).isNotNull();
    }

    @Test()
    @TestTransaction
    void createTimesheetWithEntityIsNullException() {

        Throwable throwable = null;

        try {
            create.create(null);
        } catch (Throwable e) {
            throwable = e;
        }

        Truth.assertThat(throwable).isNotNull();
        Truth.assertThat(throwable).isInstanceOf(EntityPersistException.class);
        Truth.assertThat(throwable).hasMessageThat().isEqualTo("The entity to persist is null");
        Truth.assertThat(throwable).hasCauseThat().isNull();
    }

    @Test()
    @TestTransaction
    void createTimesheetWithEntityWithoutSubmitter() {

        Throwable throwable = null;

        try {
            Timesheet timesheet = 
                Timesheet.builder()
                    .build();

            create.create(timesheet);
        } catch (Throwable e) {
            throwable = e;
        }

        Truth.assertThat(throwable).isNotNull();
        Truth.assertThat(throwable).isInstanceOf(EntityPersistException.class);
        Truth.assertThat(throwable).hasMessageThat().isEqualTo("The timesheet submitter is required");
        Truth.assertThat(throwable).hasCauseThat().isNull();
    }

    @Test()
    @TestTransaction
    void createTimesheetWithEntityWithoutActivityDuration() {

        Throwable throwable = null;

        try {

            Person submitter = 
                personRepository
                    .findByFullName("M%", "Ashworth")
                    .get(0); 
                
            Timesheet timesheet = 
                Timesheet.builder()
                    .submitter(submitter)
                    .build();

            create.create(timesheet);
        } catch (Throwable e) {
            throwable = e;
        }

        Truth.assertThat(throwable).isNotNull();
        Truth.assertThat(throwable).isInstanceOf(EntityPersistException.class);
        Truth.assertThat(throwable).hasMessageThat().isEqualTo("The timesheet duration or activity start & end must be provided");
        Truth.assertThat(throwable).hasCauseThat().isNull();
    }

    /**
     * The timesheets overlap because
     * First timesheet:     10:00 -> 12:00
     * Second timesheet:    10:30 -> 11:30
     */
    @Test()
    @TestTransaction
    void createTimesheetWithEntityWithOverlappingActivityDuration() {

        Throwable throwable = null;

        try {
            DateRange dateRange = 
                DateRangeUtility.forHour("2020-10-10T10:00:00", 2L);
            
            Person submitter = 
                personRepository
                    .findByFullName("M%", "Ashworth")
                    .get(0); 
                
            Timesheet timesheet = 
                Timesheet.builder()
                    .submitter(submitter)
                    .activityStart(dateRange.getFrom())
                    .activityEnd(dateRange.getTo())
                    .build();

            create.create(timesheet);


            dateRange = 
                DateRangeUtility.forHour("2020-10-10T10:30:00", 1L);

            timesheet = 
                Timesheet.builder()
                    .submitter(submitter)
                    .activityStart(dateRange.getFrom())
                    .activityEnd(dateRange.getTo())
                    .build();

            create.create(timesheet);

        } catch (Throwable e) {
            throwable = e;
        }

        Truth.assertThat(throwable).isNotNull();
        Truth.assertThat(throwable).isInstanceOf(EntityPersistException.class);
        Truth.assertThat(throwable).hasMessageThat().isEqualTo("The timesheet activity start & end time overlaps an existing timesheet");
        Truth.assertThat(throwable).hasCauseThat().isNull();
    }

    /**
     * The timesheets are sequential because
     * First timesheet:     10:00 -> 12:00
     * Second timesheet:    12:00 -> 13:00
     */
    @Test()
    @TestTransaction
    void createTimesheetWithEntityWithSequentialActivityDuration() {

       
        DateRange dateRange = 
            DateRangeUtility.forHour("2020-10-10T10:00:00", 2L);
        

        Person submitter = 
            personRepository
                .findByFullName("M%", "Ashworth")
                .get(0); 
            
        Timesheet timesheet = 
            Timesheet.builder()
                .submitter(submitter)
                .activityStart(dateRange.getFrom())
                .activityEnd(dateRange.getTo())
                .comment("First entry")
                .build();

        create.create(timesheet);

        Truth.assertThat(timesheet.getId()).isNotNull();
        Truth.assertThat(timesheet.getActivityStart()).isEqualTo(LocalDateTime.parse("2020-10-10T10:00:00"));
        Truth.assertThat(timesheet.getActivityEnd()).isEqualTo(LocalDateTime.parse("2020-10-10T12:00:00"));
        Truth.assertThat(timesheet.getSubmitter()).isNotNull();
        Truth.assertThat(timesheet.getSubmitter().getPartyId()).isEqualTo(submitter.getPartyId());
        Truth.assertThat(timesheet.getComment()).isEqualTo("First entry");
        Truth.assertThat(timesheet.getApproved()).isNull();
        Truth.assertThat(timesheet.getApprover()).isNull();

        dateRange = 
            DateRangeUtility.forHour("2020-10-10T12:00:00", 1L);

        timesheet = 
            Timesheet.builder()
                .submitter(submitter)
                .activityStart(dateRange.getFrom())
                .activityEnd(dateRange.getTo())
                .comment("Second entry")
                .build();

        create.create(timesheet);

        Truth.assertThat(timesheet.getId()).isNotNull();
        Truth.assertThat(timesheet.getActivityStart()).isEqualTo(LocalDateTime.parse("2020-10-10T12:00:00"));
        Truth.assertThat(timesheet.getActivityEnd()).isEqualTo(LocalDateTime.parse("2020-10-10T13:00:00"));
        Truth.assertThat(timesheet.getSubmitter()).isNotNull();
        Truth.assertThat(timesheet.getSubmitter().getPartyId()).isEqualTo(submitter.getPartyId());
        Truth.assertThat(timesheet.getComment()).isEqualTo("Second entry");
        Truth.assertThat(timesheet.getApproved()).isNull();
        Truth.assertThat(timesheet.getApprover()).isNull();
    }
}
