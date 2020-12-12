package threesixty.hr.management.shared.services.work.order;

import java.util.function.Function;

import org.eclipse.scout.rt.shared.services.lookup.LookupRow;

public class WorkOrderTypeToLookupRow implements Function<WorkOrderType, LookupRow<String>> {

	@Override
	public LookupRow<String> apply(
			final WorkOrderType workOrderType) {
		
		return new LookupRow<String>(
				mapToCells(workOrderType), 
				String.class);
	}

	private Object[] mapToCells(
			final WorkOrderType workOrderType) {
		
		return new Object[] {
				workOrderType.getName()				/* key		*/,
				workOrderType.getName()				/* text		*/,
				null 								/* iconId	*/,
				workOrderType.getDescription()		/* tool tip	*/
		};
	}
}
