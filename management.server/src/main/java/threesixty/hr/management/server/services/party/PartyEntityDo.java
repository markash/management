package threesixty.hr.management.server.services.party;

import org.eclipse.scout.rt.dataobject.DoEntity;
import org.eclipse.scout.rt.dataobject.DoValue;
import org.eclipse.scout.rt.dataobject.TypeName;
import org.eclipse.scout.rt.dataobject.TypeVersion;

import threesixty.hr.management.shared.services.employee.PartyType;

@TypeName("Party")
@TypeVersion("scout-8.0.0.027")
public class PartyEntityDo extends DoEntity {

	public DoValue<PartyType> type() {
		return doValue("type");
	}
}
