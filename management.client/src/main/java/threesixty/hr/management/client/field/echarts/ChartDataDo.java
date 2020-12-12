package threesixty.hr.management.client.field.echarts;

import org.eclipse.scout.rt.dataobject.DoEntity;
import org.eclipse.scout.rt.dataobject.DoValue;
import org.eclipse.scout.rt.dataobject.TypeName;

@TypeName("management.EChartsData")
public class ChartDataDo extends DoEntity {

	public DoValue<String> type() {
		return doValue("type");
	}
}
