package threesixty.hr.core.work.order;

import java.util.List;
import java.util.Objects;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import threesixty.hr.core.comparison.PropertyComparisonException;
import threesixty.hr.core.comparison.ValueChanges;
import threesixty.hr.core.exception.EntityModifyException;
import threesixty.hr.infrastructure.work.order.PanacheWorkOrderRepository;

@ApplicationScoped
public class WorkOrderFacade implements IRetrieveWorkOrder,
										IModifyWorkOrder {
	private static final Logger LOG = LoggerFactory.getLogger(WorkOrderFacade.class);
	
	
	@Inject
	PanacheWorkOrderRepository workOrderRepository;
	
	
	/**
	 * Retrieve all the work orders
	 * @return The list of work orders
	 */
	public List<WorkOrder> retrieveAll() {
	
		LOG.info("Retrieve all work orders");
		
		return workOrderRepository
				.retrieveAll();
	}
	
	/**
	 * Retrieve the work order by the unique identifier
	 * @return The work order
	 */
	@Override
	public WorkOrder retrieveById(final Long id) {
		
		return workOrderRepository
				.findById(id);
	}
	
	/**
	 * Modify the work order
	 * @param workOrder The work order that contains an existing work order identifier and the updated values.
	 * @return The modified work order
	 * @throws EntityModifyException If there is an exception modifying the entity
	 */
	@Override
	@Transactional
	public WorkOrder modify(
			final WorkOrder workOrder) {
		
		if (Objects.isNull(workOrder)) {
			throw new EntityModifyException("The work order to modify is null");
		}
		
		if (Objects.isNull(workOrder.getId()) || workOrder.getId() < 1L) {
			throw new EntityModifyException("The entity id for modification cannot be null or non-positive");
		}
		
		WorkOrder persisted = (WorkOrder) WorkOrder.findById(workOrder.getId()) /* NOSONAR */;
    	
		if (Objects.isNull(persisted)) {
			throw new EntityModifyException(String.format("The work order with id %s to modify is not found", workOrder.getId()));
		}
		
		try {

			ValueChanges valueChanges = persisted.compareWith(workOrder);
			
			if (valueChanges.hasChanged(WorkOrder.FIELD_TYPE)) {
				
				WorkOrderType workOrderType = 
						WorkOrderType.findById(workOrder.getType().getName()) /* NOSONAR */;
				
				persisted.setType(workOrderType);
			}
			
			persisted.flush();
			
			return persisted;
		} catch (PropertyComparisonException e) {

			throw new EntityModifyException("Unable to determine changes in object instances");
		}
	}
}
