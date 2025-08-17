# TEA — Meditation & Wellness Android App

A simple, calming Android app that helps users practice meditation, manage stress and anxiety, improve sleep, and follow guided yoga sessions. The app is written in Java with XML layouts and uses Material Design components for a clean and intuitive experience.

## Features
- Guided yoga sessions (intro and exercises)
- Sleep aid screen
- Stress management (two guided flows)
- Anxiety support screen
- Psychologists directory screen
- Dashboard with basic progress visualization (custom `BarChartView`)
- Favorites management via `FavoritesManager`
- Profile screen

Launcher activity: `StartActivity`.

## Tech stack
- Language: Java (source/target compatibility Java 11)
- UI: Android XML layouts, Material Design, ConstraintLayout
- Android Gradle Plugin: 8.7.2
- Compile SDK: 34
- Target SDK: 34
- Min SDK: 34 (Android 14+ devices/emulators only)
- Key libraries:
  - `androidx.appcompat:appcompat:1.7.0`
  - `com.google.android.material:material:1.12.0`
  - `androidx.activity:activity:1.9.3`
  - `androidx.constraintlayout:constraintlayout:2.2.0`
  - Test: `junit:4.13.2`, `androidx.test.ext:junit:1.2.1`, `androidx.test.espresso:espresso-core:3.6.1`

## Project structure
```
Tea/
├─ app/
│  ├─ src/main/java/lb/edu/ul/tea/
│  │  ├─ StartActivity.java        // Launcher
│  │  ├─ MainActivity.java
│  │  ├─ DashboardActivity.java
│  │  ├─ SleepActivity.java
│  │  ├─ yoga1.java
│  │  ├─ yoga_ex.java
│  │  ├─ stress1.java
│  │  ├─ stress2.java
│  │  ├─ Anexiety.java             // Anxiety screen
│  │  ├─ psychologists.java
│  │  ├─ profile1.java
│  │  ├─ FavoritesManager.java
│  │  └─ BarChartView.java         // Custom chart view
│  ├─ src/main/res/layout/         // XML layouts (activity_*.xml, fragment_*.xml)
│  ├─ src/main/AndroidManifest.xml
│  └─ build.gradle.kts
├─ build.gradle.kts (root)
├─ settings.gradle.kts
├─ gradle.properties
└─ gradle/libs.versions.toml
```

## Getting started
### Prerequisites
- Android Studio (latest) with:
  - Android SDK 34
  - JDK 17 configured for Gradle (required by recent AGP versions)
- A device or emulator running Android 14 (API 34)+

### Open in Android Studio
1. File → Open → select the project root folder.
2. Let Gradle sync finish.
3. Choose a device/emulator (Android 14+), then Run.

### Build from the command line
From the project root:
```bash
# Build debug APK
./gradlew :app:assembleDebug

# Install on a connected device/emulator
./gradlew :app:installDebug

# Unit tests (local JVM)
./gradlew test

# Instrumented tests (device/emulator required)
./gradlew connectedAndroidTest
```
The debug APK will be at `app/build/outputs/apk/debug/app-debug.apk`.

## Configuration
- Application ID: `lb.edu.ul.tea`
- Launcher activity: `StartActivity`
- Declared permissions: `ANSWER_PHONE_CALLS`, `BODY_SENSORS`
- Release build uses `proguard-android-optimize.txt` plus `app/proguard-rules.pro`

## Notes and troubleshooting
- Min SDK is set to 34. If you need to run on older devices, lower `minSdk` in `app/build.gradle.kts` and re-test thoroughly.
- If Gradle sync/build fails due to JDK mismatch, set the Gradle JDK to 17 in Android Studio (File → Settings → Build, Execution, Deployment → Build Tools → Gradle → Gradle JDK).
- Ensure you have Android SDK Platform 34 installed via the SDK Manager.

## Contributing
1. Fork the repo and create a feature branch.
2. Make your changes with clear commit messages.
3. Run `./gradlew test` and verify the app builds and runs.
4. Open a Pull Request with a clear description and screenshots when applicable.

## License
No license has been specified. If you plan to use this project, please add a `LICENSE` file or contact the authors for permissions.
 
