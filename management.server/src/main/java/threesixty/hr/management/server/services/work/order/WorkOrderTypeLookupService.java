package threesixty.hr.management.server.services.work.order;

import java.util.List;

import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;
import org.eclipse.scout.rt.shared.services.lookup.ILookupRow;

import threesixty.hr.management.shared.services.DirectNamedEntityNameLookup;
import threesixty.hr.management.shared.services.work.order.IWorkOrderTypeLookupService;
import threesixty.hr.management.shared.services.work.order.WorkOrderType;
import threesixty.hr.management.shared.services.work.order.WorkOrderTypeToLookupRow;

public class WorkOrderTypeLookupService implements IWorkOrderTypeLookupService {

	private final DirectNamedEntityNameLookup<WorkOrderType, String> lookup =
			DirectNamedEntityNameLookup.Builder
				.forDirectNamedEntity(WorkOrderType.class, String.class)
				.withSupplier(() -> BEANS.get(WorkOrderTypeResourceClient.class).retrieveAll())
				.withMapper(new WorkOrderTypeToLookupRow())
				.build();
			
	@Override
	public List<? extends ILookupRow<String>> getDataByKey(
			final ILookupCall<String> call) {
		
		return lookup.search(call.getKey());
	}

	@Override
	public List<? extends ILookupRow<String>> getDataByText(
			final ILookupCall<String> call) {

		return lookup.search(call.getText());
	}

	@Override
	public List<? extends ILookupRow<String>> getDataByAll(
			final ILookupCall<String> call) {

		return lookup.retrieveAll();
	}

	@Override
	public List<? extends ILookupRow<String>> getDataByRec(
			final ILookupCall<String> call) {
		
		return lookup.retrieveAll();
	}
}
