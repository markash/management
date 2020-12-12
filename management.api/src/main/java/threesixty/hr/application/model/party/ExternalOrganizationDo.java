package threesixty.hr.application.model.party;

import threesixty.hr.utility.ObjectUtility;

public class ExternalOrganizationDo extends PartyDo {

	public String getName() {

		return ObjectUtility.nvl(
				getNameAsString(PartyNameTypeDo.CorporateName), 
				getNameAsString(PartyNameTypeDo.Nickname));
	}
}
