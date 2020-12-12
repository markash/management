package threesixty.hr.management.server.services.work.order;

import org.mapstruct.Mapper;

@Mapper(config = WorkOrderToDataMappingConfig.class)
public abstract class WorkOrderToDataMapper {

	
//	@Mapping(target = "assignedTo", source = "assignedTo.name")
//	public abstract RolesRowData toRolesRowData(WorkOrderRole workOrderRole);
}
