package threesixty.hr.application.model.work.timesheet;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.mapstruct.Mapper;

import threesixty.hr.application.model.work.order.WorkOrderBaseDo;
import threesixty.hr.core.party.PartyNameType;
import threesixty.hr.core.party.PartyNamingStrategies;
import threesixty.hr.core.work.order.WorkOrder;
import threesixty.hr.core.work.timesheet.Timesheet;

@Mapper(config = TimesheetMappingConfig.class)
public abstract class TimesheetMapper {

	@Inject
    EntityManager em;
	
	PartyNamingStrategies namingStrategies = new PartyNamingStrategies();
	
	public abstract WorkOrderBaseDo toWorkOrder(final WorkOrder workOrder);
	
	public abstract TimesheetDo toTimesheet(final Timesheet timesheet);
	
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