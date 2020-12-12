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

import org.eclipse.scout.rt.client.ui.form.fields.AbstractFormField;
import org.eclipse.scout.rt.dataobject.IDataObjectMapper;
import org.eclipse.scout.rt.platform.BEANS;

public abstract class AbstractEChartsField extends AbstractFormField implements IEChartsField {

	@Override
	protected void initConfig() {
		super.initConfig();
		setOption(getConfiguredOption());
		setData(getConfiguredData());
	}

	protected ChartOptionDo getConfiguredOption() {
		return IEChartsField.readChartOption(IEChartsField.class.getResourceAsStream("/echarts/options.json"));
	}
	
	protected ChartDataDo getConfiguredData() {
		return IEChartsField.readChartData(IEChartsField.class.getResourceAsStream("/echarts/dataset.json"));
	}

	@Override
	public void setOption(
			final ChartOptionDo option) {
		
		propertySupport.setProperty(PROP_CHART_OPTION, option);
	}

	@Override
	public void setOption(
			final String option) {
		
		propertySupport.setProperty(
				PROP_CHART_OPTION,
				BEANS.get(IDataObjectMapper.class).readValue(option, ChartOptionDo.class));
	}

	@Override
	public ChartOptionDo getOption() {
		return (ChartOptionDo) propertySupport.getProperty(PROP_CHART_OPTION);
	}
	
	@Override
	public void setData(
			final ChartDataDo data) {
		
		propertySupport.setProperty(PROP_CHART_DATA, data);
	}

	@Override
	public void setData(
			final String data) {
		
		propertySupport.setProperty(
				PROP_CHART_DATA,
				BEANS.get(IDataObjectMapper.class).readValue(data, ChartDataDo.class));
	}

	@Override
	public ChartDataDo getData() {
		return (ChartDataDo) propertySupport.getProperty(PROP_CHART_DATA);
	}
}
