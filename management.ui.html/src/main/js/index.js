// import your custom JS files here
export {default as ChartField} from './field/chart/ChartField';
export {default as ChartFieldAdapter} from './field/chart/ChartFieldAdapter';

export {default as EChartsField} from './field/echarts/EChartsField';
export {default as EChartsFieldAdapter} from './field/echarts/EChartsFieldAdapter';

// Define namespace and put it onto window (necessary for model variants, e.g. @ModelVariant(${classPrefixLowerCase}.Example)
import * as self from './index.js';
export default self;
window.management = Object.assign(window.management || {}, self);
