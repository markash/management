package threesixty.hr.management.client.outline;

import java.util.Optional;

import org.eclipse.scout.rt.client.ui.basic.tree.ITreeNode;
import org.eclipse.scout.rt.client.ui.basic.tree.ITreeNodeFilter;
import org.eclipse.scout.rt.client.ui.desktop.outline.AbstractSearchOutline;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import threesixty.hr.management.client.employee.EmployeeTablePage;
import threesixty.hr.management.shared.Icons;
import threesixty.hr.management.shared.employee.EmployeeTablePageData;
import threesixty.hr.management.shared.employee.PartySearchFormData;
import threesixty.hr.management.shared.services.employee.IEmployeeManager;

/**
 * @author Mark Ashworth
 */
@Order(2000)
public class SearchOutline extends AbstractSearchOutline {

	private static final Logger LOG = LoggerFactory.getLogger(SearchOutline.class);

	private ISearchNodeFilter filter = null;
	
	@Override
	protected void execInitTree() {
		
		if (filter == null) {
			
			filter = new SearchNodeFilter();
			
			addNodeFilter(filter);
		}
	}
	
	@Override
	protected void execSearch(
			final String query) {
		
		LOG.info("Search started");
		
		filter.setSearch(query);
	
		ITreeNode node = getRootNode();
		
		Optional<ITreeNode> employeeNode = 
				node.getChildNodes().stream()
					.filter(n -> n instanceof EmployeeTablePage)
					.findFirst();
		
		
		
		
		if (employeeNode.isEmpty()) {
			
			employeeNode = Optional.ofNullable(new EmployeeTablePage());
			
			addChildNode(node, employeeNode.get());
		}
		
		PartySearchFormData formData = new PartySearchFormData();
		formData.getName().setValue(query);
		
		SearchFilter filter = ((EmployeeTablePage) employeeNode.get()).getSearchFilter();
		filter.setFormData(formData);
		
		
		EmployeeTablePageData pageData = BEANS.get(IEmployeeManager.class).retrieveTableData(filter);
		
//		EmployeeTablePageData pageData = new EmployeeTablePageData();
//		EmployeeTableRowData rowData = pageData.addRow();
//		rowData.setEmployeeId(BigInteger.valueOf(22L));
//		rowData.setName("Mark Ashworth");
//		rowData.setFirstName("Mark");
//		rowData.setLastName("Ashworth");
		
		((EmployeeTablePage) employeeNode.get()).importEmployees(pageData);
	}

	
	@Override
	protected String getConfiguredIconId() {
		return Icons.Search;
	}
	
	private static interface ISearchNodeFilter extends ITreeNodeFilter {
		
		void setSearch(final String query);
	}
	
	private static final class SearchNodeFilter implements ISearchNodeFilter {

		private String query = null;
		
		@Override
		public void setSearch(final String query) {
		
			this.query = query;
		}
		
		@Override
		public boolean accept(
				final ITreeNode node, 
				final int level) {
			
			
			return true;
		}
		
	}
}
