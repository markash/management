package threesixty.hr.core.work.order;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class WorkOrderTypeFacadeTest {

	@Inject
	WorkOrderTypeFacade facade;
	
	@Test
	public void retrieveAll() {
		
		
		List<WorkOrderType> workOrderTypes = facade.retrieveAll();
		
		assertNotNull(workOrderTypes);
		
		workOrderTypes.stream().forEach(System.out::println);
	}
}
