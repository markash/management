package threesixty.hr.management.client.field.echarts;

import javax.annotation.Generated;

import org.eclipse.scout.rt.dataobject.DoEntity;
import org.eclipse.scout.rt.dataobject.DoValue;
import org.eclipse.scout.rt.dataobject.TypeName;

@TypeName("management.EChartsOption")
public class ChartOptionDo extends DoEntity {

	public DoValue<String> width() {
		return doValue("width");
	}
	
	public DoValue<String> height() {
		return doValue("height");
	}
	
	/*
	 * **************************************************************************
	 * GENERATED CONVENIENCE METHODS
	 *************************************************************************/

	@Generated("DoConvenienceMethodsGenerator")
	public ChartOptionDo withHeight(final String height) {
		height().set(height);
		return this;
	}

	@Generated("DoConvenienceMethodsGenerator")
	public String getHeight() {
		return height().get();
	}
	
	@Generated("DoConvenienceMethodsGenerator")
	public ChartOptionDo withWidth(final String width) {
		width().set(width);
		return this;
	}

	@Generated("DoConvenienceMethodsGenerator")
	public String getWidth() {
		return width().get();
	}
}
