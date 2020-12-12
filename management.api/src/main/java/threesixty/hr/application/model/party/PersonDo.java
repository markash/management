package threesixty.hr.application.model.party;

import java.util.Arrays;
import java.util.stream.Collectors;

import threesixty.hr.utility.ObjectUtility;

public class PersonDo extends PartyDo {

	public String getGivenName() {
		
		return getNameAsString(PartyNameTypeDo.GivenName);
	}
	
	public String getMiddleName() {
		
		return getNameAsString(PartyNameTypeDo.MiddleName);
	}

	public String getFamilyName() {

		return getNameAsString(PartyNameTypeDo.FamilyName);
	}
	
	public String getName() {

		return Arrays.asList(
				ObjectUtility.nvl(getGivenName(), ""),
				ObjectUtility.nvl(getMiddleName(), ""),
				ObjectUtility.nvl(getFamilyName(), ""))
				.stream()
				.collect(Collectors.joining(" "));
	}
}
