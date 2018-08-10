package com.llv.rn;



import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Nullable;




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
      RNBackgroundTimerModule.emitDeviceEvent("onTimeFinished", null);
      timerStarted = false;
    }
  };


  @ReactMethod
  public void delayedTimer(Integer interval, Callback callback) {
    try {

      if (!timerStarted) {
        timerStarted = true;
        Log.i("RNTimer", "delayedTimer: starting");
        RNBackgroundTimerModule.emitDeviceEvent("onTimeStarted", null);
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
