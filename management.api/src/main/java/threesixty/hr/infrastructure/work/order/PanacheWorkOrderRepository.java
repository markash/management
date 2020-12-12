package threesixty.hr.infrastructure.work.order;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import threesixty.hr.core.work.order.WorkOrder;

@ApplicationScoped
public class PanacheWorkOrderRepository implements PanacheRepository<WorkOrder> {
    
	/**
	 * Retrieve all the work orders
	 * @return The list of work orders
	 */
	public List<WorkOrder> retrieveAll() {
		
		return WorkOrder.listAll();
	}
}
