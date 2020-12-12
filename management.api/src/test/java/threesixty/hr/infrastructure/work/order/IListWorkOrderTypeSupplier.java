package threesixty.hr.infrastructure.work.order;

import java.util.List;
import java.util.function.Supplier;

import threesixty.hr.core.work.order.WorkOrderType;

public interface IListWorkOrderTypeSupplier extends Supplier<List<WorkOrderType>> {

}
