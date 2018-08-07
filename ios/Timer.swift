//
//  RepeatingTimer.swift
//  scheduleTimer
//
//  Created by Ronald Suez on 8/6/18.
//
//

class Timer {
    let timeInterval: TimeInterval
    var eventHandler: (() -> Void)?
    
    private enum State {
        case suspended
        case resumed
    }
    
    private var state: State = .suspended
    
    init(timeInterval: TimeInterval) {
        self.timeInterval = timeInterval
    }
    
    private lazy var timer: DispatchSourceTimer = {
        let t = DispatchSource.makeTimerSource()
        t.schedule(deadline: .now() + self.timeInterval, repeating: self.timeInterval)
        t.setEventHandler(handler: { [weak self] in self?.eventHandler?()})
        return t
    }()
    
    func resume () {
        if (state == .resumed) {
            return
        }
        state = .resumed
        timer.resume()
    }
    
    func suspend () {
        if (state == .suspended) {
            return
        }
        state = .suspended
        timer.suspend()
    }
    
    deinit {
        timer.setEventHandler {}
        timer.cancel()
        /**
         * should call resume in the case of timer is suspended
         */
        resume()
        
        eventHandler = nil
    }
}
