package threesixty.hr.management.server.services.work.order;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.scout.rt.platform.util.CollectionUtility;

import threesixty.hr.management.shared.services.work.order.WorkOrder;
import threesixty.hr.management.shared.services.work.order.WorkOrderRoleToRowData;
import threesixty.hr.management.shared.services.work.order.WorkOrderType;
import threesixty.hr.management.shared.work.order.WorkOrderFormData;
import threesixty.hr.management.shared.work.order.WorkOrderFormData.Roles.RolesRowData;

public class WorkOrderToFormData implements Function<WorkOrder, WorkOrderFormData>{

	private final WorkOrderFormData formData;
	
	public WorkOrderToFormData() {
		
		this.formData = new WorkOrderFormData();;
	}
	
	public WorkOrderToFormData(
			final WorkOrderFormData formData) {
		
		this.formData = formData;
	}
	
	public static WorkOrderToFormData withFormData(
			final WorkOrderFormData formData) {
		
		return new WorkOrderToFormData(formData);
	}
	
	@Override
	public WorkOrderFormData apply(
			final WorkOrder workOrder) {
		
		if (workOrder == null) { return null; }
		
		formData.setId(workOrder.getId());
		formData.getName().setValue(workOrder.getName());
		formData.getScheduledStart().setValue(workOrder.getScheduledStartDate());
		formData.getScheduledEnd().setValue(workOrder.getScheduledEndDate());
		formData.getActualStart().setValue(workOrder.getActualStartDate());
		formData.getActualEnd().setValue(workOrder.getActualEndDate());
		formData.getEstimate().setValue(workOrder.getEstimate());
		formData.getCapitalize().setValue(workOrder.getCapitalize());
		formData.getWorkOrderType().setValue(Optional.ofNullable(workOrder.getType()).map(WorkOrderType::getName).orElse(null));
		
		formData.getEpicOwner().setValue(9L);
		formData.getKeyStakeholder().setValue(5L);
		formData.getEpicDescription().setValue(EPIC_DESCRIPTION_TEMPLATE);
		formData.getDescription().setValue("Build the architectural runway for a cloud-based offering.");
		
		if (CollectionUtility.hasElements(workOrder.getRoles())) {
		
			WorkOrderRoleToRowData toRolesRowData = new WorkOrderRoleToRowData();
			
			RolesRowData[] rows =
					workOrder.getRoles().stream()
						.map(toRolesRowData)
						.collect(Collectors.toList())
						.toArray(new RolesRowData[0]);
			
			formData.getRoles().setRows(rows);
		}
		
		return formData;
	}
	
	private static final String EPIC_DESCRIPTION_TEMPLATE = 
			"<strong>For</strong> &lt;customers&gt;<br />" + 
			"<strong>who</strong> &lt;do something&gt;<br />" + 
			"<strong>the</strong> &lt;solution&gt;<br />" + 
			"<strong>is a</strong> &lt;something – the ‘how’&gt;<br />" + 
			"<strong>that</strong> &lt;provides this value&gt;<br />" + 
			"<strong>unlike</strong> &lt;competitor, current solution or non-existing solution&gt;<br />" + 
			"<strong>our solution</strong> &lt;does something better — the ‘why’&gt;<br />" 
			;
}
