package threesixty.hr.management.shared.services.employee;

import java.util.List;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;

@TunnelToServer
public interface IPartyRelationshipResource extends IService {
	
	/**
	 * Retrieve the party relationships
	 * @param party The part for which to return relationships
	 * @param types The types of party relationships to filter by, else return all
	 * @return List of party relationships
	 */
	List<PartyRelationship> retrieve(
			final Party party,
			final RelationshipType...types);
}
