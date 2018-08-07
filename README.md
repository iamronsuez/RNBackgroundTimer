
# react-native-background-timer

## Getting started

`$ npm install react-native-background-timer --save`

### Mostly automatic installation

`$ react-native link react-native-background-timer`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-background-timer` and add `RNBackgroundTimer.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNBackgroundTimer.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.llv.rn.RNBackgroundTimerPackage;` to the imports at the top of the file
  - Add `new RNBackgroundTimerPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-background-timer'
  	project(':react-native-background-timer').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-background-timer/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-background-timer')
  	```


## Usage
```javascript
import RNBackgroundTimer from 'react-native-background-timer';

// TODO: What to do with the module?
RNBackgroundTimer;
```
  