package threesixty.hr.management.client.employee;

import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractSearchForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractSearchButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.client.ui.form.fields.tabbox.AbstractTabBox;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;

import threesixty.hr.management.client.employee.PartySearchForm.MainBox.SearchButton;
import threesixty.hr.management.client.employee.PartySearchForm.MainBox.TabBox;
import threesixty.hr.management.client.employee.PartySearchForm.MainBox.TabBox.FieldBox;
import threesixty.hr.management.client.employee.PartySearchForm.MainBox.TabBox.FieldBox.NameField;
import threesixty.hr.management.shared.employee.PartySearchFormData;

@FormData(value = PartySearchFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class PartySearchForm extends AbstractSearchForm {
	
	@Override
	protected String getConfiguredTitle() {
		return TEXTS.get("Search");
	}

	public TabBox getTabBox() {
		return getFieldByClass(TabBox.class);
	}

	public NameField getNameField() {
		return getFieldByClass(NameField.class);
	}

	public SearchButton getSearchButton() {
		return getFieldByClass(SearchButton.class);
	}

	public MainBox getMainBox() {
		return getFieldByClass(MainBox.class);
	}

	public FieldBox getFieldBox() {
		return getFieldByClass(FieldBox.class);
	}

	@Order(1000)
	public class MainBox extends AbstractGroupBox {
		
		@Order(1000)
		public class TabBox extends AbstractTabBox {
			
			@Order(1000)
			public class FieldBox extends AbstractGroupBox {

				@Order(1000)
				public class NameField extends AbstractStringField {
					@Override
					protected String getConfiguredLabel() {
						return TEXTS.get("Name");
					}

					@Override
					protected int getConfiguredMaxLength() {
						return 128;
					}
				}
			}
		}

		

		@Order(2000)
		public class SearchButton extends AbstractSearchButton { }	
	}
	
	public class SearchHandler extends AbstractFormHandler { }
}
