package threesixty.hr.management.shared.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import org.eclipse.scout.rt.shared.data.basic.table.AbstractTableRowData;
import org.eclipse.scout.rt.shared.data.page.AbstractTablePageData;

import threesixty.hr.management.shared.work.order.WorkOrderTablePageData;
import threesixty.hr.management.shared.work.order.WorkOrderTablePageData.WorkOrderTableRowData;

public class CollectorUtility {

	protected abstract static class AbstractTablePageDataCollectors<T extends AbstractTableRowData, R extends AbstractTablePageData> implements Collector<T, List<T>, R> {

		@Override
		public Supplier<List<T>> supplier() {
			return ArrayList::new;
		}

		@Override
		public BiConsumer<List<T>, T> accumulator() {
			return List::add;
		}

		@Override
		public BinaryOperator<List<T>> combiner() {
			return (left, right) -> { left.addAll(right); return left; };
		}

		@Override
		public Set<Characteristics> characteristics() {
			
			return new HashSet<>(Arrays.asList(Characteristics.UNORDERED));
		}
		
	}

//	public static class WorkOrderTablePageDataCollector extends AbstractTablePageDataCollectors<WorkOrderTableRowData, WorkOrderTablePageData> {
//
//		@Override
//		public Function<List<WorkOrderTableRowData>, WorkOrderTablePageData> finisher() {
//			
//			return (list) -> {
//				
//				WorkOrderTablePageData pageData = new WorkOrderTablePageData();
//				pageData.setRows(list.toArray(new AbstractTableRowData[0]));
//				return pageData;
//			};
//		}
//	}
//	
	public static AbstractTablePageDataCollectors<WorkOrderTableRowData, WorkOrderTablePageData> toWorkOrderTablePageData() {
		
		return new AbstractTablePageDataCollectors<WorkOrderTableRowData, WorkOrderTablePageData>() {

			@Override
			public Function<List<WorkOrderTableRowData>, WorkOrderTablePageData> finisher() {
				
				return (list) -> {
					
					WorkOrderTablePageData pageData = new WorkOrderTablePageData();
					pageData.setRows(list.toArray(new AbstractTableRowData[0]));
					return pageData;
				};
			}
		};
	}
}
