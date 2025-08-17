🌿 TEA — Meditation & Wellness Android App
TEA is a simple and calming Android application designed to help users practice meditation, manage stress and anxiety, improve sleep, and follow guided yoga sessions.
The app is built with Java and XML layouts, following Material Design principles for a modern and intuitive user experience.

✨ Features
🧘 Guided yoga sessions (intro + exercises)

😴 Sleep aid screen

🌱 Stress management (two guided flows)

💭 Anxiety support screen

👩‍⚕️ Psychologists directory

📊 Dashboard with basic progress visualization (BarChartView)

⭐ Favorites management with FavoritesManager

👤 Profile screen

👉 Launcher activity: StartActivity

🛠 Tech Stack
Language: Java (Java 11 compatibility)

UI: Android XML layouts, Material Design, ConstraintLayout

Gradle Plugin: 8.7.2

Compile/Target SDK: 34

Min SDK: 34 (Android 14+ only)

Dependencies:

androidx.appcompat:appcompat:1.7.0

com.google.android.material:material:1.12.0

androidx.activity:activity:1.9.3

androidx.constraintlayout:constraintlayout:2.2.0

Testing: junit:4.13.2, androidx.test.ext:junit:1.2.1, androidx.test.espresso:espresso-core:3.6.1

📂 Project Structure
swift
Copy
Edit
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
│  ├─ src/main/res/layout/         // XML layouts
│  ├─ src/main/AndroidManifest.xml
│  └─ build.gradle.kts
├─ build.gradle.kts (root)
├─ settings.gradle.kts
├─ gradle.properties
└─ gradle/libs.versions.toml
🚀 Getting Started
Prerequisites
Android Studio (latest version)

Android SDK 34

JDK 17 (required by latest AGP)

Device/Emulator running Android 14 (API 34)+

Open in Android Studio
Open Android Studio → File → Open → Project Root

Wait for Gradle sync to complete

Select an Android 14+ device/emulator and run the app

Build from CLI
bash
Copy
Edit
# Build debug APK
./gradlew :app:assembleDebug

# Install on device/emulator
./gradlew :app:installDebug

# Run unit tests
./gradlew test

# Run instrumented tests
./gradlew connectedAndroidTest
➡️ Debug APK output: app/build/outputs/apk/debug/app-debug.apk

⚙️ Configuration
Application ID: lb.edu.ul.tea

Launcher Activity: StartActivity

Permissions:

ANSWER_PHONE_CALLS

BODY_SENSORS

Release build: uses proguard-android-optimize.txt + app/proguard-rules.pro

🐞 Troubleshooting
App requires Android 14+ (minSdk = 34). To support older versions, lower minSdk in app/build.gradle.kts and retest.

If Gradle fails due to JDK issues, set Gradle JDK to 17:
File → Settings → Build, Execution, Deployment → Build Tools → Gradle → Gradle JDK.

Ensure Android SDK Platform 34 is installed via SDK Manager.

🤝 Contributing
Fork this repository

Create a feature branch (git checkout -b feature/your-feature)

Commit your changes with clear messages

Run tests (./gradlew test) and ensure the app builds

Submit a Pull Request with a detailed description and screenshots (if applicable)

📜 License
This project has no license specified.
If you plan to use or distribute this app, please add a LICENSE file or contact the authors for permission.

