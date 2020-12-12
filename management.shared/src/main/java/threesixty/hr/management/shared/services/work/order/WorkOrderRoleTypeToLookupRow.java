package threesixty.hr.management.shared.services.work.order;

import java.util.function.Function;

import org.eclipse.scout.rt.shared.services.lookup.LookupRow;

public class WorkOrderRoleTypeToLookupRow implements Function<WorkOrderRoleType, LookupRow<String>> {

	@Override
	public LookupRow<String> apply(
			final WorkOrderRoleType workOrderRoleType) {
		
		return new LookupRow<String>(
				mapToCells(workOrderRoleType), 
				String.class);
	}

	private Object[] mapToCells(
			final WorkOrderRoleType workOrderRoleType) {
		
		return new Object[] {
				workOrderRoleType.getName()			/* key		*/,
				workOrderRoleType.getName()			/* text		*/,
				null 								/* iconId	*/,
				workOrderRoleType.getDescription()	/* tool tip	*/
		};
	}
}
