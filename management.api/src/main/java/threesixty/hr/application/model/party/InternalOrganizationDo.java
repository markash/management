package threesixty.hr.application.model.party;

import threesixty.hr.utility.ObjectUtility;

public class InternalOrganizationDo extends PartyDo {

	public String getName() {

		return ObjectUtility.nvl(
				getNameAsString(PartyNameTypeDo.DepartmentName), 
				getNameAsString(PartyNameTypeDo.TeamName));
	}
}
