package threesixty.hr.management.shared.employee;

import javax.annotation.Generated;

import org.eclipse.scout.rt.shared.data.basic.table.AbstractTableRowData;
import org.eclipse.scout.rt.shared.data.page.AbstractTablePageData;

/**
 * <b>NOTE:</b><br>
 * This class is auto generated by the Scout SDK. No manual modifications
 * recommended.
 */
@Generated(value = "threesixty.hr.management.client.employee.EmployeeCalendarTablePage", comments = "This class is auto generated by the Scout SDK. No manual modifications recommended.")
public class EmployeeCalendarTablePageData extends AbstractTablePageData {
	private static final long serialVersionUID = 1L;

	@Override
	public EmployeeCalendarTableRowData addRow() {
		return (EmployeeCalendarTableRowData) super.addRow();
	}

	@Override
	public EmployeeCalendarTableRowData addRow(int rowState) {
		return (EmployeeCalendarTableRowData) super.addRow(rowState);
	}

	@Override
	public EmployeeCalendarTableRowData createRow() {
		return new EmployeeCalendarTableRowData();
	}

	@Override
	public Class<? extends AbstractTableRowData> getRowType() {
		return EmployeeCalendarTableRowData.class;
	}

	@Override
	public EmployeeCalendarTableRowData[] getRows() {
		return (EmployeeCalendarTableRowData[]) super.getRows();
	}

	@Override
	public EmployeeCalendarTableRowData rowAt(int index) {
		return (EmployeeCalendarTableRowData) super.rowAt(index);
	}

	public void setRows(EmployeeCalendarTableRowData[] rows) {
		super.setRows(rows);
	}

	public static class EmployeeCalendarTableRowData extends AbstractTableRowData {
		private static final long serialVersionUID = 1L;
	}
}
