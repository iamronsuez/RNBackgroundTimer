
import { NativeModules } from 'react-native';
import RNTimerView from './src/RNTimerView'
import RNTimerEventEmitter from './src/RNTimerEventEmitter'

const { RNBackgroundTimer } = NativeModules;

export default {
  RNBackgroundTimer,
  RNTimerView,
  RNTimerEventEmitter
};
