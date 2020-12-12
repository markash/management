package threesixty.hr.management.client.employee;

import org.eclipse.scout.rt.client.dto.Data;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.ITableRow;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractDateColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractLongColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

import threesixty.hr.management.client.employee.EmployeeTablePage.Table;
import threesixty.hr.management.shared.employee.EmployeeTablePageData;
import threesixty.hr.management.shared.employee.PartySearchFormData;
import threesixty.hr.management.shared.services.employee.IEmployeeManager;

@Data(EmployeeTablePageData.class)
public class EmployeeTablePage extends AbstractPageWithTable<Table> {
	
	@Override
	protected String getConfiguredTitle() {
		return TEXTS.get("EmployeeTablePage");
	}

	@Override
	protected void execLoadData(
			final SearchFilter filter) {
		
		if (filter.getFormData() == null) {
			filter.setFormData(new PartySearchFormData());
		}
		
		importPageData(BEANS.get(IEmployeeManager.class).retrieveTableData(filter));
	}

	@Override
	protected IPage<?> execCreateChildPage(
			final ITableRow row) {
		
		Long id = (Long) row.getCell(getTable().getEmployeeIdColumn()).getValue();
		
		return new EmployeeNodePage(id);
	}
	
	public void importEmployees(
			final EmployeeTablePageData pageData) {
		
		getTable().deleteRows(getTable().getRows());
		
		removeChildNodesInternal(getChildNodes(), true, true);
		
		
		importPageData(pageData);
		
		firePageChanged();
	}
	
	public class Table extends AbstractTable {

		@Override
		protected boolean getConfiguredAutoResizeColumns() { return true; }

		public DateOfBirthColumn getDateOfBirthColumn() {
			return getColumnSet().getColumnByClass(DateOfBirthColumn.class);
		}

		public IdentityNumberColumn getIdentityNumberColumn() {
			return getColumnSet().getColumnByClass(IdentityNumberColumn.class);
		}

		public OrganizationNameColumn getOrganizationNameColumn() {
			return getColumnSet().getColumnByClass(OrganizationNameColumn.class);
		}

		public NameColumn getNameColumn() {
			return getColumnSet().getColumnByClass(NameColumn.class);
		}

		public LastNameColumn getLastNameColumn() {
			return getColumnSet().getColumnByClass(LastNameColumn.class);
		}

		public FirstNameColumn getFirstNameColumn() {
			return getColumnSet().getColumnByClass(FirstNameColumn.class);
		}

		public EmployeeIdColumn getEmployeeIdColumn() {
			return getColumnSet().getColumnByClass(EmployeeIdColumn.class);
		}

		@Order(1000)
		public class EmployeeIdColumn extends AbstractLongColumn {
			
			@Override
			protected String getConfiguredHeaderText() { return TEXTS.get("Id"); }

			@Override
			protected int getConfiguredWidth() { return 10; }
			
			@Override
			protected boolean getConfiguredDisplayable() { return false; }
		}


		@Order(1500)
		public class NameColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("Name");
			}

			@Override
			protected int getConfiguredWidth() {
				return 30;
			}
		}

		@Order(2000)
		public class FirstNameColumn extends AbstractStringColumn {
			
			@Override
			protected String getConfiguredHeaderText() { return TEXTS.get("FirstName"); }

			@Override
			protected int getConfiguredWidth() { return 15; }
		}

		@Order(3000)
		public class LastNameColumn extends AbstractStringColumn {
			
			@Override
			protected String getConfiguredHeaderText() { return TEXTS.get("LastName"); }

			@Override
			protected int getConfiguredWidth() { return 15; }
		}

		@Order(4000)
		public class DateOfBirthColumn extends AbstractDateColumn {
			@Override
			protected String getConfiguredHeaderText() { return TEXTS.get("BirthDate"); }

			@Override
			protected int getConfiguredWidth() { return 15; }
		}

		@Order(5000)
		public class IdentityNumberColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() { return TEXTS.get("Identity"); }

			@Override
			protected int getConfiguredWidth() { return 15; }
		}


		@Order(6000)
		public class OrganizationNameColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("Organization");
			}

			@Override
			protected int getConfiguredWidth() {
				return 20;
			}
		}
		
	}
}
