//
//  RNBackgroundTimer.m
//  
//
//  Created by Ronald Suez on 8/6/18.
// 
//



#import "React/RCTBridgeModule.h"
#import "React/RCTEventEmitter.h"

@interface RCT_EXTERN_REMAP_MODULE(RNBackgroundTimer, RNBackgroundTimer, RCTEventEmitter)

RCT_EXTERN_METHOD(delayedTimer: (nonnull int *) time callback:(RCTResponseSenderBlock)callback)
@end

