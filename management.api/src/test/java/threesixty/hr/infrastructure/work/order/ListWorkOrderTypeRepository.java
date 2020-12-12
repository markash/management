package threesixty.hr.infrastructure.work.order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import threesixty.hr.core.work.order.IWorkOrderTypeRepository;
import threesixty.hr.core.work.order.WorkOrderType;

/** TODO Not required */
@Alternative
public class ListWorkOrderTypeRepository implements IWorkOrderTypeRepository {

	private List<WorkOrderType> items;
	
	@Inject
	public ListWorkOrderTypeRepository(
			final IListWorkOrderTypeSupplier supplier) {
		
		this.items = new ArrayList<>(supplier.get());
	}
	
	public void withItem(
			final WorkOrderType...items) {
		
		this.items.addAll(Arrays.asList(items));
	}
	
	
	@Override
	public List<WorkOrderType> retrieveAll() {
		
		return Collections.unmodifiableList(items);
	}
}
