package threesixty.hr.infrastructure.work.timesheet;

import java.time.LocalDateTime;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import com.google.common.truth.Truth;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import threesixty.hr.core.date.DateRange;
import threesixty.hr.core.party.Person;
import threesixty.hr.core.work.order.WorkOrder;
import threesixty.hr.core.work.timesheet.Timesheet;
import threesixty.hr.infrastructure.party.PanachePersonRepository;
import threesixty.hr.infrastructure.work.order.PanacheWorkOrderRepository;


@QuarkusTest
class PanacheTimesheetRepositoryTest {
    
    @Inject
    PanachePersonRepository personRepository;

    @Inject
    PanacheWorkOrderRepository workOrderRepository;
    
    @Inject
    PanacheTimesheetRepository timesheetRepository;
    
    @Test
    @Transactional
    void retrieve() {

        List<Person> persons = 
            personRepository.findByFullName("M%", "Ashworth");

        Truth.assertThat(persons).isNotNull();
        Truth.assertThat(persons).isNotEmpty();
        Truth.assertThat(persons).hasSize(1);

        Person submitter = persons.get(0);

        List<WorkOrder> workOrders =
            workOrderRepository.findAll().list();

        Truth.assertThat(workOrders).isNotNull();
        Truth.assertThat(workOrders).isNotEmpty();
        
        WorkOrder workOrder = workOrders.get(0);

        Timesheet timesheet = 
            Timesheet.builder()
                .submitter(submitter)
                .activityStart(LocalDateTime.parse("2020-10-10T08:00:00"))
                .activityEnd(LocalDateTime.parse("2020-10-10T08:30:00"))
                .workOrder(workOrder)
                .comment("Team Meeting")
                .build();

        timesheetRepository.persist(timesheet);

        DateRange octoberMonth = 
            DateRange.Builder
                .fromStartOfMonth("2020-06-10")
                .toEndOfMonth("2020-06-10")
                .build();

        Truth.assertThat(octoberMonth).isNotNull();
        Truth.assertThat(octoberMonth.getFrom()).isEqualTo(LocalDateTime.parse("2020-06-01T00:00:00"));
        Truth.assertThat(octoberMonth.getTo()).isEqualTo(LocalDateTime.parse("2020-06-30T23:59:59"));

        Truth.assertThat(timesheetRepository).isNotNull();

        List<Timesheet> results = timesheetRepository.retrieveBySubmitter(submitter.getPartyId(), octoberMonth);

        Truth.assertThat(results).isNotNull();
        Truth.assertThat(results).isNotEmpty();
    }
}
