package threesixty.hr.management.shared.employee;

import javax.annotation.Generated;

import org.eclipse.scout.rt.shared.data.form.AbstractFormData;
import org.eclipse.scout.rt.shared.data.form.fields.AbstractValueFieldData;

/**
 * <b>NOTE:</b><br>
 * This class is auto generated by the Scout SDK. No manual modifications
 * recommended.
 */
@Generated(value = "threesixty.hr.management.client.employee.PartySearchForm", comments = "This class is auto generated by the Scout SDK. No manual modifications recommended.")
public class PartySearchFormData extends AbstractFormData {
	private static final long serialVersionUID = 1L;

	public Name getName() {
		return getFieldByClass(Name.class);
	}

	public static class Name extends AbstractValueFieldData<String> {
		private static final long serialVersionUID = 1L;
	}
}