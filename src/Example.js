import React from 'react';
import { StyleSheet, Text, View, TouchableOpacity, NativeModules } from 'react-native';
import RNtimerEventEmitter from './RNTimerEventEmitter'
import RNtimerView from './RNTimerView'
export default class Example extends React.Component {
  
 state = {
   time: 60
 }
  render () {
    return (
      <RNtimerView>
      {({delayedTimer, status}) => (
        <View style={styles.container}>
          {this.state.started && <Text>Timer set for {this.state.time} seconds.</Text>}
          <View style={styles.row}>
            <View style={styles.separator} />
            <TouchableOpacity style={styles.button} onPress={() => delayedTimer(this.state.time)}>
              <Text style={styles.text}>Set Interval</Text>
            </TouchableOpacity>
          </View>
          <Text>Timer {status}</Text>
        </View>
      )}
    </RNtimerView>
    )
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
  row: {
    flexDirection: 'row'
  },
  text: {
    color: 'white'
  },
  button: {
    padding: 20,
    width: 120,
    backgroundColor: 'blue'
  },
  separator: {
    width: 10
  }
});
