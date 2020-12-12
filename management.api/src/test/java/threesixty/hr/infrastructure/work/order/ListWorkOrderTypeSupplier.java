package threesixty.hr.infrastructure.work.order;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import threesixty.hr.core.work.order.WorkOrderType;

@ApplicationScoped
public class ListWorkOrderTypeSupplier implements IListWorkOrderTypeSupplier {

	@Override
	public List<WorkOrderType> get() {
		
		return Arrays.asList(new WorkOrderType[] {
			WorkOrderType.builder()
				.name("Test")
				.description("Description")
				.capitalize(true)
				.estimate(false)
				.processed(LocalDateTime.now())
				.build(),
				
			WorkOrderType.builder()
				.name("Test")
				.description("Description")
				.capitalize(true)
				.estimate(false)
				.processed(LocalDateTime.now())
				.build()
		});
	}

}
