import {RemoteApp} from '@eclipse-scout/core';
import * as management from './index';

Object.assign({}, management); // Use import so that it is not marked as unused

new RemoteApp().init();
