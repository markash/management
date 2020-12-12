package threesixty.hr.core.work.order;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class WorkOrderTypeFacade implements IRetrieveWorkOrderTypes {

	private final IWorkOrderTypeRepository workOrderTypeRepository;
	
	@Inject
	public WorkOrderTypeFacade(
			final IWorkOrderTypeRepository workOrderTypeRepository) {
		
		this.workOrderTypeRepository = workOrderTypeRepository;
	}
	
	
	@Override
	public List<WorkOrderType> retrieveAll() {
		
		return workOrderTypeRepository.retrieveAll();
	}
}
