package com.llv.rn;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

public class RNBackgroundTimerModule extends ReactContextBaseJavaModule {

  private static ReactApplicationContext reactContext;
  private Handler handler;
  public Boolean timerStarted = false;

  public RNBackgroundTimerModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
    this.handler = new Handler(Looper.getMainLooper());
  }

  @Override
  public String getName() {
    return "RNBackgroundTimer";
  }



  private Runnable runnableCode = new Runnable() {
    @Override
    public void run() {
      Log.i("RNTimer", "delayedTimer: finished");
      TimerModule.emitDeviceEvent("onTimeFinished", null);
      timerStarted = false;
    }
  };


  @ReactMethod
  public void delayedTimer(Integer interval, Callback callback) {
    try {

      if (!timerStarted) {
        timerStarted = true;
        Log.i("RNTimer", "delayedTimer: starting");
        TimerModule.emitDeviceEvent("onTimeStarted", null);
        this.handler.postDelayed(runnableCode, Long.valueOf(interval * 1000));
        callback.invoke(null, interval);

      } else {
        callback.invoke("timer already started", null);
      }
    } catch (Exception e) {
      callback.invoke(e.getMessage(), null);
    }
  }

  private static void emitDeviceEvent(String eventName, @Nullable WritableMap eventData) {
    reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit(eventName, eventData);
  }
}