package threesixty.hr.management.ui.html.field.echarts;

import org.eclipse.scout.rt.dataobject.IDataObjectMapper;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.ui.html.IUiSession;
import org.eclipse.scout.rt.ui.html.json.IJsonAdapter;
import org.eclipse.scout.rt.ui.html.json.JsonProperty;
import org.eclipse.scout.rt.ui.html.json.form.fields.JsonFormField;
import org.json.JSONArray;
import org.json.JSONObject;

import threesixty.hr.management.client.field.echarts.ChartDataDo;
import threesixty.hr.management.client.field.echarts.ChartOptionDo;
import threesixty.hr.management.client.field.echarts.IEChartsField;

public class JsonEChartsField extends JsonFormField<IEChartsField> {

	public JsonEChartsField(
			final IEChartsField model, 
			final IUiSession uiSession, 
			final String id, 
			final IJsonAdapter<?> parent) {
		
		super(model, uiSession, id, parent);
	}

	@Override
	public String getObjectType() {
		return "management.EChartsField";
	}

	@Override
	protected void initJsonProperties(
			final IEChartsField model) {
		
		super.initJsonProperties(model);
		putJsonProperty(new JsonProperty<IEChartsField>(IEChartsField.PROP_CHART_OPTION, model) {
			@Override
			protected ChartOptionDo modelValue() {
				return getModel().getOption();
			}

			@Override
			public Object prepareValueForToJson(final Object value) {
				return new JSONObject(BEANS.get(IDataObjectMapper.class).writeValue(value));
			}
		});
		
		putJsonProperty(new JsonProperty<IEChartsField>(IEChartsField.PROP_CHART_DATA, model) {
			@Override
			protected ChartDataDo modelValue() {
				return getModel().getData();
			}

			@Override
			public Object prepareValueForToJson(final Object value) {
				return new JSONObject(BEANS.get(IDataObjectMapper.class).writeValue(value));
			}
		});
	}

	protected JSONArray intArrayToJson(int[][] value) {
		JSONArray res = new JSONArray();
		for (int[] subArr : value) {
			JSONArray subRes = new JSONArray();
			for (int v : subArr) {
				subRes.put(v);
			}
			res.put(subRes);
		}
		return res;
	}
}
