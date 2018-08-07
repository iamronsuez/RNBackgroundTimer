//
//  Timer.m
//  scheduleTimer
//
//  Created by Ronald Suez on 8/6/18.
//  Copyright © 2018 Facebook. All rights reserved.
//



#import "React/RCTBridgeModule.h"
#import "React/RCTEventEmitter.h"

@interface RCT_EXTERN_REMAP_MODULE(RNBackgroundTimer, RNBackgroundTimer, RCTEventEmitter)

RCT_EXTERN_METHOD(delayedTimer: (nonnull int *) time callback:(RCTResponseSenderBlock)callback)
@end

