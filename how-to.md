# How to Run the App

## Setup

### Generate Gradle Wrapper
```bash
gradle wrapper --gradle-version 9.4.1
```
Creates the Gradle wrapper files (`gradlew` and `gradlew.bat`) that allow you to build the project without having Gradle installed globally. This project uses Gradle 9.4.1 (latest as of March 2026).

## Running the App

### Start an Emulator First
Before installing the app, you need a running emulator or connected device.

**List available emulators:**
```bash
~/Library/Android/sdk/emulator/emulator -list-avds
```

**Start an emulator:**
```bash
~/Library/Android/sdk/emulator/emulator -avd Pixel_5 &
```
Or:
```bash
~/Library/Android/sdk/emulator/emulator -avd Medium_Phone_API_36.1 &
```

**Check connected devices:**
```bash
~/Library/Android/sdk/platform-tools/adb devices
```

### Install on Device/Emulator
```bash
./gradlew installDebug
```
Builds the debug version of the app and installs it on a connected Android device or running emulator.

### Build the App
```bash
./gradlew build
```
Compiles the project and creates the APK file.

### Clean Build
```bash
./gradlew clean
```
Removes all build artifacts and output files.

### Assemble APK
```bash
./gradlew assembleDebug
```
Creates the debug APK without running tests.

## Restarting the App

**About ADB:** Android Debug Bridge (adb) is a command-line tool that allows you to communicate with Android devices/emulators. It's included in the Android SDK at `~/Library/Android/sdk/platform-tools/`.

**Setup ADB:** Add it to your PATH to use `adb` commands:
```bash
export PATH=$PATH:~/Library/Android/sdk/platform-tools
```

### Force Stop and Restart
```bash
adb shell am force-stop com.example.counterapp
adb shell am start -n com.example.counterapp/.MainActivity
```
Force stops the app and launches it again.

### Reinstall and Run
```bash
./gradlew installDebug
adb shell am start -n com.example.counterapp/.MainActivity
```
Reinstalls the app and starts it.

### Quick Restart
```bash
adb shell am force-stop com.example.counterapp && adb shell am start -n com.example.counterapp/.MainActivity
```
One-liner to stop and restart the app.

## Alternative: Android Studio

Open the project in Android Studio and click the green "Run" button (or press Shift+F10) to build and run the app on an emulator or device.

## Current Configuration

- **Gradle Version:** 9.4.1
- **Android Gradle Plugin:** 8.7.3
- **Kotlin:** 2.1.0
- **Compile SDK:** 35
- **Target SDK:** 35
- **Min SDK:** 24
- **Java Version:** 17
