/*
 * Copyright (c) 2019 BSI Business Systems Integration AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     BSI Business Systems Integration AG - initial API and implementation
 */
package threesixty.hr.management.client.field.echarts;

import java.io.InputStream;

import org.eclipse.scout.rt.client.ui.form.fields.IFormField;
import org.eclipse.scout.rt.dataobject.IDataObjectMapper;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.util.Assertions;

public interface IEChartsField extends IFormField {

	String PROP_CHART_OPTION = "option";
	String PROP_CHART_DATA = "data";
	
	ChartOptionDo getOption();

	void setOption(String option);

	void setOption(final ChartOptionDo option);

	static ChartOptionDo readChartOption(
			final InputStream is) {
		
		Assertions.assertNotNull(is);
		return BEANS.get(IDataObjectMapper.class).readValue(is, ChartOptionDo.class);
	}
	
	ChartDataDo getData();

	void setData(String data);

	void setData(final ChartDataDo data);

	static ChartDataDo readChartData(
			final InputStream is) {
		
		Assertions.assertNotNull(is);
		return BEANS.get(IDataObjectMapper.class).readValue(is, ChartDataDo.class);
	}
}
