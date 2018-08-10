// RNtimer.js

import { NativeModules, NativeEventEmitter } from 'react-native'

class RNTimer extends NativeEventEmitter {
  constructor(nativeModule) {
    super(nativeModule);
  }
}
export default new RNTimer(NativeModules.RNBackgroundTimer)