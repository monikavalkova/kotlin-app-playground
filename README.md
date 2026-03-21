# Kotlin Counter App

![Build Status](https://github.com/monikavalkova/kotlin-app-playground/workflows/Android%20CI/badge.svg)
![Tests](https://github.com/monikavalkova/kotlin-app-playground/workflows/Code%20Quality/badge.svg)

My first Android mobile app - a simple counter application built with Kotlin and Jetpack Compose. This project serves as a learning playground for Android development.

## Features
- Material Design 3
- Jetpack Compose UI
- DataStore for persistent counter storage (survives app restarts)
- Single button counter starting from 0

## Setup

### Prerequisites
- Android Studio or Android SDK installed
- Java 17

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

## Troubleshooting

### Icon Not Updating
If the app icon doesn't change after installation, Android may be caching the old icon. Try:

**Restart the launcher:**
```bash
adb shell pm clear com.google.android.apps.nexuslauncher
```

**Or reboot the emulator:**
```bash
adb reboot
```

**Or uninstall and reinstall:**
```bash
adb uninstall com.example.counterapp
./gradlew installDebug
```

## Alternative: Android Studio

Open the project in Android Studio and click the green "Run" button (or press Shift+F10) to build and run the app on an emulator or device.

## Technical Configuration

- **Gradle Version:** 9.4.1
- **Android Gradle Plugin:** 8.7.3
- **Kotlin:** 2.1.0
- **Compile SDK:** 35
- **Target SDK:** 35
- **Min SDK:** 24
- **Java Version:** 17

## Dependencies

- AndroidX Core KTX 1.15.0
- Lifecycle Runtime KTX 2.8.7
- Activity Compose 1.9.3
- Compose BOM 2024.12.01
- Material3
- DataStore Preferences 1.1.1

## Testing

### Running Unit Tests

```bash
./gradlew testDebugUnitTest
```

This runs unit tests for the ViewModel and business logic. The project includes tests for:
- Counter starting at zero
- Incrementing counter
- Resetting counter
- Multiple increments

### Test Architecture

The app uses a testable architecture with:
- **Repository pattern**: Separates data access from business logic
- **ViewModel**: Manages UI state
- **Fake repository**: For fast, isolated testing
- **Coroutine testing**: Using `StandardTestDispatcher` and Turbine

### Test Report

After running tests, view the HTML report at:
```
build/reports/tests/testDebugUnitTest/index.html
```

## Continuous Integration

### GitHub Actions Workflows

The project includes automated CI/CD pipelines:

#### 1. Android CI (`android-ci.yml`)
Runs on every PR and push to master:
- ✅ Runs all unit tests
- ✅ Builds debug APK
- ✅ Uploads test reports and APK artifacts
- ✅ Publishes test results in PR

#### 2. Code Quality (`code-quality.yml`)
Runs on every PR:
- ✅ Runs lint checks
- ✅ Uploads lint reports

### Status Badges
Add these to your README (replace `username/repo`):
```markdown
![Build Status](https://github.com/username/repo/workflows/Android%20CI/badge.svg)
![Tests](https://github.com/username/repo/workflows/Code%20Quality/badge.svg)
```

### Viewing Results
- Test results appear as checks on PRs
- Download APK artifacts from workflow runs
- View detailed test reports in Actions tab

