package threesixty.hr.management.client.common.field;

public class AbstractDateField extends org.eclipse.scout.rt.client.ui.form.fields.datefield.AbstractDateField {

	@Override
	protected String getConfiguredDateFormatPattern() {
		
		return "yyyy-MM-dd";
	}
}
