
import React, { Component } from 'react'
import { NativeModules, StyleSheet } from 'react-native'
import RNtimerEventEmitter from './RNTimerEventEmitter'

const RNtimer = NativeModules.RNBackgroundTimer

export default class RNTimerView extends Component {

  state = {
    time: 0,
    started: false,
    stopped: true
  }


  getStatus = () => {
    const states = ['started', 'stopped']
    const started = this.state.started && !this.state.stopped
    const stopped = !this.state.started && this.state.stopped
    const currentStatus = [started, stopped].findIndex((i) => i)
    return states[currentStatus]
  }

  componentDidMount() {

    RNtimerEventEmitter.addListener('onTimeStarted', (res) => {
      this.setState({started: true, stopped: false}, () => {
        if(typeof this.props.onTimeStarted === 'function') {
          this.props.onTimeStarted(res)
        }
      })
    })

    RNtimerEventEmitter.addListener('onTimeFinished', (res) => {
      this.setState({started: false, stopped: true}, () => {
        if(typeof this.props.onTimeFinished === 'function') {
          this.props.onTimeFinished(res)
        }
      })
    })
  }

  componentWillUnmount() {
    RNtimerEventEmitter.removeAllListeners()
  }

  delayedTimer(interval, callback = () => null, options = {singleCall: true}) {
    RNtimer.delayedTimer(interval, callback)
  }

  render () {
    return this.props.children({
      delayedTimer: this.delayedTimer,
      status: this.getStatus()
    })
  }
}

