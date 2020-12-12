package threesixty.hr.application.work.order;


import static io.restassured.RestAssured.given;
import static net.javacrumbs.jsonunit.JsonMatchers.jsonEquals;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.panache.mock.PanacheMock;
import io.quarkus.test.junit.QuarkusTest;
import threesixty.hr.application.ResourcePaths;
import threesixty.hr.core.party.PartyType;
import threesixty.hr.core.party.Person;
import threesixty.hr.core.work.order.WorkOrder;
import threesixty.hr.core.work.order.WorkOrderBuilder;

@QuarkusTest
class WorkOrderResourceTest {

	@Test
	void testHelloEndpoint() throws Exception {
		
		Person party = new Person();
		party.setPartyId(2L);
		party.setFirstName("Test");
		party.setLastName("Person");
		party.setType(PartyType.PERSON);
		party.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse("1974-05-31"));
		
		WorkOrder workOrder = 
				WorkOrderBuilder.builder()
					.withId(1L)
					.withName("Test Work Order")
					.withScheduledStart(LocalDate.parse("2020-12-12").atStartOfDay())
					.withScheduledStart(LocalDate.parse("2020-12-31").atStartOfDay())
					.withActualStart(LocalDate.parse("2020-12-15").atStartOfDay())
					.withActualStart(LocalDate.parse("2021-01-12").atStartOfDay())
					.type()
						.withName("Test Type")
						.withDescription("Test description")
						.withEstimate(true)
						.withCapitalize(false)
						.end()
					.role()
						.withAssignedTo(party)
						.end()
					.build()
					;
		
		List<PanacheEntityBase> workOrders = Arrays.asList(workOrder);
		
		PanacheMock.mock(WorkOrder.class);
		Mockito.when(WorkOrder.listAll()).thenReturn(workOrders);
		
		given()
			.when().get(ResourcePaths.WORK_ORDER)
			.then()
			.statusCode(200)
			.body(jsonEquals(EXPECTED));
		
	}
	
	private static final String EXPECTED = 
	"[{\r\n"
	+ "    \"id\": 1,\r\n"
	+ "    \"name\": \"Test Work Order\",\r\n"
	+ "    \"actualStart\": \"2021-01-12T00:00:00\",\r\n"
	+ "    \"roles\": [\r\n"
	+ "        {\r\n"
	+ "            \"assignedTo\": {\r\n"
	+ "                \"id\": 2,\r\n"
	+ "                \"name\": \"Test Person\"\r\n"
	+ "            }\r\n"
	+ "        }\r\n"
	+ "    ],\r\n"
	+ "    \"scheduledStart\": \"2020-12-31T00:00:00\",\r\n"
	+ "    \"type\": {\r\n"
	+ "        \"capitalize\": false,\r\n"
	+ "        \"description\": \"Test description\",\r\n"
	+ "        \"estimate\": true,\r\n"
	+ "        \"name\": \"Test Type\"\r\n"
	+ "    }\r\n"
	+ "}]";
}

