package threesixty.hr.core.work.order;

import static com.google.common.truth.Truth.assertThat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import threesixty.hr.core.comparison.EntityComparison;
import threesixty.hr.core.comparison.PropertyComparison;
import threesixty.hr.core.comparison.ValueChange;
import threesixty.hr.core.comparison.ValueChanges;

class WorkOrderComparisonTest {

    @Test
    void compareEntityNull() throws Exception {

        WorkOrder w01 = null;

        WorkOrderType type = WorkOrderType.builder().name("type01").description("Some description").build();

        WorkOrder w02 = new WorkOrder();
        w02.setType(type);

        PropertyComparison<WorkOrder> comparison = PropertyComparison.of(WorkOrder.class, "type");
        
        ValueChange result =  comparison.compare(w01, w02);

        assertThat(result).isNotNull();
        assertThat(result.isDifferent()).isTrue();
    }

    @Test
    void comparePropertyNull() throws Exception {

        WorkOrder w01 = new WorkOrder();

        WorkOrderType type = WorkOrderType.builder().name("type01").description("Some description").build();

        WorkOrder w02 = new WorkOrder();
        w02.setType(type);

        PropertyComparison<WorkOrder> comparison = PropertyComparison.of(WorkOrder.class, "type");
        
        ValueChange result =  comparison.compare(w01, w02);

        assertThat(result).isNotNull();
        assertThat(result.isDifferent()).isTrue();
    }

    /**
     * Test that although the WorkType instances differ that the WorkType.name is used to perform
     * the comparison. Therefore type01 compared to type02 is 0 (same).
     */
    @Test
    void comparePropertyInstanceDiffer() throws Exception {

        WorkOrderType type01 = WorkOrderType.builder().name("type01").description("Some description").build();

        WorkOrder w01 = new WorkOrder();
        w01.setType(type01);

        WorkOrderType type02 = WorkOrderType.builder().name("type01").description("Some description").build();

        WorkOrder w02 = new WorkOrder();
        w02.setType(type02);

        PropertyComparison<WorkOrder> comparison = PropertyComparison.of(WorkOrder.class, "type");
        
        ValueChange result =  comparison.compare(w01, w02);

        assertThat(result).isNotNull();
        assertThat(result.isSame()).isTrue();
    }

    @Test
    void comparePropertyValuesDiffer() throws Exception {

        WorkOrderType type01 = WorkOrderType.builder().name("type01").description("Some description").build();

        WorkOrder w01 = new WorkOrder();
        w01.setType(type01);

        WorkOrderType type02 = WorkOrderType.builder().name("type02").description("Some description").build();

        WorkOrder w02 = new WorkOrder();
        w02.setType(type02);

        PropertyComparison<WorkOrder> comparison = PropertyComparison.of(WorkOrder.class, "type");
        
        ValueChange result =  comparison.compare(w01, w02);

        assertThat(result).isNotNull();
        assertThat(result.isDifferent()).isTrue();
    }


    @Test
    void compareMultiplePropertiesOneDiffers() throws Exception {

        WorkOrder w01 = new WorkOrder();
        w01.setType(WorkOrderType.builder().name("type01").description("Some description").build());
        w01.setScheduledStart(LocalDateTime.parse("2020-05-31T08:00:00"));

        WorkOrder w02 = new WorkOrder();
        w02.setType(WorkOrderType.builder().name("type02").description("Some description").build());
        w02.setScheduledStart(LocalDateTime.parse("2020-05-31T08:00:00"));

        EntityComparison<WorkOrder> comparison = EntityComparison.of(WorkOrder.class, "type", "scheduledStart");
        
        ValueChanges result =  comparison.compare(w01, w02);

        assertThat(result).isNotNull();
        assertThat(result.isDifferent()).isTrue();

        List<ValueChange> differences = 
            result.differences()
                .collect(Collectors.toList());

        assertThat(differences).isNotNull();
        assertThat(differences).isNotEmpty();
        assertThat(differences.get(0).getPropertyName()).isEqualTo("type");

    }

    @Test
    void compareMultiplePropertiesNoneDiffers() throws Exception {

        WorkOrder w01 = new WorkOrder();
        w01.setType(WorkOrderType.builder().name("type01").description("Some description").build());
        w01.setScheduledStart(LocalDateTime.parse("2020-05-31T08:00:00"));

        WorkOrder w02 = new WorkOrder();
        w02.setType(WorkOrderType.builder().name("type01").description("Some description").build());
        w02.setScheduledStart(LocalDateTime.parse("2020-05-31T08:00:00"));

        EntityComparison<WorkOrder> comparison = EntityComparison.of(WorkOrder.class, "type", "scheduledStart");
        
        ValueChanges result =  comparison.compare(w01, w02);

        assertThat(result).isNotNull();
        assertThat(result.isSame()).isTrue();
    }

    @Test
    void compareOnWorkOrder() throws Exception {

        WorkOrder w01 = new WorkOrder();
        w01.setType(WorkOrderType.builder().name("type01").description("Some description").build());
        w01.setScheduledStart(LocalDateTime.parse("2020-05-31T08:00:00"));

        WorkOrder w02 = new WorkOrder();
        w02.setType(WorkOrderType.builder().name("type01").description("Some description").build());
        w02.setScheduledStart(LocalDateTime.parse("2020-05-31T08:00:00"));

        ValueChanges result =  w01.compareWith(w02);

        assertThat(result).isNotNull();
        assertThat(result.isSame()).isTrue();
    }
}
