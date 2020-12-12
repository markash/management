package threesixty.hr.management.client.work.order;

import java.util.Set;

import org.eclipse.scout.rt.client.dto.Data;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.action.menu.IMenuType;
import org.eclipse.scout.rt.client.ui.action.menu.TableMenuType;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractBooleanColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractDateColumn;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.CollectionUtility;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

import threesixty.hr.management.client.common.column.AbstractIdColumn;
import threesixty.hr.management.client.common.column.AbstractNameColumn;
import threesixty.hr.management.client.work.order.WorkOrderTablePage.Table;
import threesixty.hr.management.shared.services.work.order.IWorkOrderEngine;
import threesixty.hr.management.shared.work.order.WorkOrderTablePageData;

@Data(WorkOrderTablePageData.class)
public class WorkOrderTablePage extends AbstractPageWithTable<Table> {
	@Override
	protected String getConfiguredTitle() {
		return TEXTS.get("WorkOrders");
	}

//	@Override
//	protected IPage<?> execCreateChildPage(
//			final ITableRow row) {
//		
//		Long id = (Long) row.getCell(getTable().getIdColumn()).getValue();
//		
//		return new WorkOrderNodePage(id);
//	}
	
	@Override
	protected void execLoadData(
			final SearchFilter filter) {
		
		importPageData(BEANS.get(IWorkOrderEngine.class).retrievePageData(filter));
	}

	public class Table extends AbstractTable {

		@Override
		protected boolean getConfiguredAutoResizeColumns() {
			return true;
		}
		
		public IdColumn getIdColumn() {
			return getColumnSet().getColumnByClass(IdColumn.class);
		}

		public ScheduledStartColumn getScheduledStartColumn() {
			return getColumnSet().getColumnByClass(ScheduledStartColumn.class);
		}

		public ScheduledEndColumn getScheduledEndColumn() {
			return getColumnSet().getColumnByClass(ScheduledEndColumn.class);
		}

		public ActualStartColumn getActualStartColumn() {
			return getColumnSet().getColumnByClass(ActualStartColumn.class);
		}

		public ActualEndColumn getActualEndColumn() {
			return getColumnSet().getColumnByClass(ActualEndColumn.class);
		}

		public CapitalizeColumn getCapitalizeColumn() {
			return getColumnSet().getColumnByClass(CapitalizeColumn.class);
		}

		public EstimateColumn getEstimateColumn() {
			return getColumnSet().getColumnByClass(EstimateColumn.class);
		}

		public NameColumn getNameColumn() {
			return getColumnSet().getColumnByClass(NameColumn.class);
		}

		@Order(1000)
		public class NameColumn extends AbstractNameColumn { }

		@Order(2000)
		public class ScheduledStartColumn extends AbstractDateColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("ScheduledStart");
			}

			@Override
			protected int getConfiguredWidth() {
				return 15;
			}
		}

		@Order(3000)
		public class ScheduledEndColumn extends AbstractDateColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("ScheduledEnd");
			}

			@Override
			protected int getConfiguredWidth() {
				return 15;
			}
		}

		@Order(4000)
		public class ActualStartColumn extends AbstractDateColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("ActualStart");
			}

			@Override
			protected int getConfiguredWidth() {
				return 15;
			}
		}

		@Order(5000)
		public class ActualEndColumn extends AbstractDateColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("ActualEnd");
			}

			@Override
			protected int getConfiguredWidth() {
				return 15;
			}
		}

		@Order(6000)
		public class CapitalizeColumn extends AbstractBooleanColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("Capitalize");
			}

			@Override
			protected int getConfiguredWidth() {
				return 15;
			}
		}

		@Order(7000)
		public class EstimateColumn extends AbstractBooleanColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("Estimate");
			}

			@Override
			protected int getConfiguredWidth() {
				return 15;
			}
		}

		public class IdColumn extends AbstractIdColumn {
			
			@Override
			protected boolean getConfiguredDisplayable() {
				return false;
			}
		}

		@Order(1000)
		public class ViewMenu extends AbstractMenu {
			@Override
			protected String getConfiguredText() {
				return TEXTS.get("View");
			}

			@Override
			protected Set<? extends IMenuType> getConfiguredMenuTypes() {
				return CollectionUtility.hashSet(TableMenuType.SingleSelection);
			}

			@Override
			protected void execAction() {
				
				WorkOrderForm form = 
						new WorkOrderForm()
							.withId((Long) getTable().getSelectedRow().getCell(getIdColumn()).getValue());
				
				form.startModify();
			}
		}
	}
}
