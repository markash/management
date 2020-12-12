package threesixty.hr.management.ui.html;

import org.eclipse.scout.rt.platform.Bean;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.ui.html.IUiSession;
import org.eclipse.scout.rt.ui.html.json.AbstractJsonObjectFactory;
import org.eclipse.scout.rt.ui.html.json.IJsonAdapter;

import threesixty.hr.management.client.field.chart.IChartField;
import threesixty.hr.management.client.field.echarts.IEChartsField;
import threesixty.hr.management.ui.html.field.chart.JsonChartField;
import threesixty.hr.management.ui.html.field.echarts.JsonEChartsField;

@Bean
@Order(100)
public class JsonObjectFactory extends AbstractJsonObjectFactory {

	@Override
	public IJsonAdapter<?> createJsonAdapter(
			final Object model, 
			final IUiSession session, 
			final String id, 
			final IJsonAdapter<?> parent) {

		if (model instanceof IChartField) {
			return new JsonChartField((IChartField) model, session, id, parent);
		}

		if (model instanceof IEChartsField) {
			return new JsonEChartsField((IEChartsField) model, session, id, parent);
		}
		
		return null;
	}
}
