package threesixty.hr.management.server.services.work.order;

import java.util.List;

import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;
import org.eclipse.scout.rt.shared.services.lookup.ILookupRow;

import threesixty.hr.management.shared.services.DirectNamedEntityNameLookup;
import threesixty.hr.management.shared.services.work.order.IWorkOrderRoleTypeLookupService;
import threesixty.hr.management.shared.services.work.order.WorkOrderRoleType;
import threesixty.hr.management.shared.services.work.order.WorkOrderRoleTypeToLookupRow;

public class WorkOrderRoleTypeLookupService implements IWorkOrderRoleTypeLookupService {

	private final DirectNamedEntityNameLookup<WorkOrderRoleType, String> lookup =
			DirectNamedEntityNameLookup.Builder
				.forDirectNamedEntity(WorkOrderRoleType.class, String.class)
				.withSupplier(() -> BEANS.get(WorkOrderRoleTypeResourceClient.class).retrieveAll())
				.withMapper(new WorkOrderRoleTypeToLookupRow())
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
