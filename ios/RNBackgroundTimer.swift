//
//  RNBackgroundTimer.swift
//  
//
//  Created by Ronald Suez on 8/6/18.
//  
//

import Foundation

@objc(RNBackgroundTimer)
class RNBackgroundTimer: RCTEventEmitter {
    private var timer: Timer?
    
    // we need to override this method and
    // return an array of event names that we can listen to
    override func supportedEvents() -> [String]! {
        return ["onTimerStarted", "onTimerTicked", "onTimeFinished"]
    }
    
    
    @objc func delayedTimer(_ interval: Int, callback: RCTResponseSenderBlock) {
        self.timer = Timer(timeInterval: TimeInterval(interval))
        self.timer?.eventHandler = {
            if((self.bridge) != nil) {
                self.sendEvent(withName: "onTimeFinished", body: ["interval": interval])
            }
            self.timer?.suspend()
        }
        self.timer?.resume()
        callback([interval])
    }
    override static func requiresMainQueueSetup() -> Bool {
        return true
    }
}
