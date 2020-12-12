package threesixty.hr.management.shared.services.work.order;

import java.util.function.Function;

import threesixty.hr.management.shared.work.order.WorkOrderFormData.Roles.RolesRowData;

public class WorkOrderRoleToRowData implements Function<WorkOrderRole, RolesRowData> {

	@Override
	public RolesRowData apply(
			final WorkOrderRole workOrderRole) {
		
		RolesRowData rowData = new RolesRowData();
		
		if (workOrderRole != null) {
			
			if (workOrderRole.getAssignedTo() != null) {
			
				rowData.setAssignedToId(workOrderRole.getAssignedTo().getId());
				rowData.setAssignedToName(workOrderRole.getAssignedTo().getName());
			}
			
			rowData.setRole(workOrderRole.getRole());
			rowData.setEffectiveFrom(workOrderRole.getEffectiveFromDate());
			rowData.setEffectiveTo(workOrderRole.getEffectiveToDate());
		}
		
		return rowData;
	}
}
