package threesixty.hr.infrastructure.work.order;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import threesixty.hr.core.work.order.IWorkOrderTypeRepository;
import threesixty.hr.core.work.order.WorkOrderType;

@ApplicationScoped
public class PanacheWorkOrderTypeRepository implements IWorkOrderTypeRepository {

	@Override
	public List<WorkOrderType> retrieveAll() {
		
		return WorkOrderType.listAll();
	}
}
