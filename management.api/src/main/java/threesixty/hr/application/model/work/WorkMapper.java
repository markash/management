package threesixty.hr.application.model.work;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import threesixty.hr.application.model.work.order.WorkOrderDo;
import threesixty.hr.application.model.work.order.WorkOrderRoleDo;
import threesixty.hr.application.model.work.order.WorkOrderRoleTypeDo;
import threesixty.hr.application.model.work.order.WorkOrderTypeDo;
import threesixty.hr.application.model.work.timesheet.TimesheetDo;
import threesixty.hr.core.party.PartyNameType;
import threesixty.hr.core.party.PartyNamingStrategies;
import threesixty.hr.core.work.order.WorkOrder;
import threesixty.hr.core.work.order.WorkOrderRole;
import threesixty.hr.core.work.order.WorkOrderRoleType;
import threesixty.hr.core.work.order.WorkOrderType;
import threesixty.hr.core.work.timesheet.Timesheet;

@Mapper(config = WorkMappingConfig.class)
public abstract class WorkMapper {

	@Inject
    EntityManager em;
	
	PartyNamingStrategies namingStrategies = new PartyNamingStrategies();
	
	/*
	 * Map from Data object to Entity object
	 */
	public abstract WorkOrder toWorkOrder(final WorkOrderDo workOrderDo);
	
	public abstract WorkOrderType toWorkOrderType(final WorkOrderTypeDo workOrderTypeDo);
	
	/*
	 * Map from Entity object to Data object
	 */
	public abstract WorkOrderDo toWorkOrderDo(final WorkOrder workOrder);
	
	public abstract WorkOrderTypeDo toWorkOrderTypeDo(final WorkOrderType workOrderType);
	
	@Mapping(target = "assignedTo.id", source = "assignedTo.partyId")
	@Mapping(target = "effectiveFrom", source = "effectiveFrom")
	@Mapping(target = "effectiveTo", source = "effectiveTo")
	@Mapping(target = "role", source = "roleType.name")
	@Mapping(target = "roleDescription", source = "roleType.description")
	public abstract WorkOrderRoleDo toWorkOrderRoleDo(final WorkOrderRole workOrderRole);
	
	public abstract WorkOrderRoleTypeDo toWorkOrderRoleTypeDo(final WorkOrderRoleType workOrderRoleType);
	
	public abstract TimesheetDo toTimesheetDo(final Timesheet timesheet);
	
	/**
     * Map a party name type to a string
     * @param partyNameType The party name type
     * @return The name of the party name type
     */
    public String map(
    		final PartyNameType partyNameType) {
        
    	return partyNameType.name;
    }
}