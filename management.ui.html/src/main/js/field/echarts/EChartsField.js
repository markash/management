import {FormField} from '@eclipse-scout/core';

import * as echarts from 'echarts/index';

export default class EChartsField extends FormField {

  constructor() {
    super();
    this.chart = null;
    this.option = null;
    this.data = null;
  }


  _init(model) {
    super._init(model);
    this._setOption(this.option);
    this._setData(this.data);
  }

  _render() {
    this.addContainer(this.$parent, 'echarts-field');
    this.addLabel();
    this.addMandatoryIndicator();

    // The additional DIV is required because ECharts has its own resize handler which
    // checks the parent element in the DOM to find its dimensions.
    this.addFieldContainer(this.$parent.makeDiv());
    this.addField(this.$fieldContainer.appendElement('<div style="min-height: 400px; width: 100%;">', 'echarts'));

    this.addStatus();
  }

  _renderProperties() {
    super._renderProperties();
    this._renderChart();
  }

  _remove() {
    super._remove();
    if (this.chart) {
      this.chart.dispose();
      this.chart = null;
    }
  }

  setOption(option) {
    this.setProperty('option', option);
  }

  _setOption(option) {
    if (typeof option === 'string') {
      option = JSON.parse(option);
    }
    this._setProperty('option', option);
  }

  setData(data) {
    this.setProperty('data', data);
  }

  _setData(data) {
    if (typeof data === 'string') {
      data = JSON.parse(data);
    }
    this._setProperty('data', data);
  }

  _renderChart() {
    if (this.chart) {
      this.chart.dispose();
    }

    this.chart = echarts.init(this.$field[0], null, this.option);

    this.chart.setOption(this.data);
  }
}
