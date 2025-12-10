# 🚀 Portfolio - Kotlin Multiplatform Application

![Kotlin Multiplatform](https://img.shields.io/badge/Kotlin-Multiplatform-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![Compose](https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=for-the-badge&logo=jetpack-compose&logoColor=white)
![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![iOS](https://img.shields.io/badge/iOS-000000?style=for-the-badge&logo=ios&logoColor=white)
![Web](https://img.shields.io/badge/Web-FF6B6B?style=for-the-badge&logo=html5&logoColor=white)
![Desktop](https://img.shields.io/badge/Desktop-00599C?style=for-the-badge&logo=windows&logoColor=white)

A modern, cross-platform portfolio application built with **Kotlin Multiplatform** and **Compose Multiplatform**, showcasing software engineering skills, cryptography implementations, and API integrations.

---

## ✨ Features

### 🏠 Home Screen
- **Personal Profile**: Display of professional information
- **Skills Showcase**: Technology stack and expertise
- **Project Portfolio**: Highlighted projects and achievements
- **Contact Information**: Easy access to professional contact details

### 🌌 NASA Integration
- **Astronomy Picture of the Day**: Real-time integration with NASA's APOD API
- **Space Exploration**: Beautiful space imagery with detailed descriptions
- **Educational Content**: Learn about astronomy through daily updated content

### 💰 Crypto-Coin Tracker
- **Cryptocurrency Monitoring**: Track digital currency prices
- **Real-time Data**: Up-to-date cryptocurrency information
- **Market Insights**: Financial data visualization

### 🔐 Cryptography Tools
A comprehensive suite of classical and modern cryptographic algorithms:
- **ADFGVX Cipher**: Military-grade WWI encryption system
- **Affine Cipher**: Mathematical substitution cipher
- **Playfair Cipher**: Digraph substitution cipher with key matrix visualization
- **RSA**: Public-key cryptography (implementation in progress)
- **DSA**: Digital Signature Algorithm support

---

## 🛠️ Technology Stack

### Core Technologies
- **Kotlin Multiplatform**: Shared codebase across all platforms
- **Compose Multiplatform**: Modern declarative UI framework
- **Kotlin/Wasm**: WebAssembly support for web deployment

### Libraries & Frameworks
- **Ktor**: HTTP client for API communication
- **Kotlinx Serialization**: JSON parsing and serialization
- **Kotlinx DateTime**: Date and time utilities
- **Kotlinx Coroutines**: Asynchronous programming
- **DataStore**: Persistent storage for Android

### Supported Platforms
- ✅ **Android** (SDK 24+)
- ✅ **iOS** (iOS 14+)
- ✅ **Desktop** (Windows, macOS, Linux)
- ✅ **Web** (WebAssembly)

---

## 📁 Project Structure

```
Portfolio/
├── composeApp/               # Shared multiplatform code
│   ├── src/
│   │   ├── commonMain/       # Common code for all platforms
│   │   │   ├── kotlin/
│   │   │   │   └── jakub/portfolio/
│   │   │   │       ├── App.kt                  # Main application
│   │   │   │       ├── ADFGVX.kt              # ADFGVX cipher
│   │   │   │       ├── Affini.kt              # Affine cipher
│   │   │   │       ├── Playfair.kt            # Playfair cipher
│   │   │   │       ├── RSA.kt                 # RSA implementation
│   │   │   │       ├── DSA.kt                 # DSA implementation
│   │   │   │       ├── TextEdit.kt            # Text utilities
│   │   │   │       ├── ui/                    # UI components
│   │   │   │       │   ├── NavigationDrawer.kt
│   │   │   │       │   └── NasaViewModel.kt
│   │   │   │       └── activity/
│   │   │   │           └── CryptoCoinScreen.kt
│   │   ├── androidMain/      # Android-specific code
│   │   ├── iosMain/          # iOS-specific code
│   │   ├── desktopMain/      # Desktop-specific code
│   │   ├── wasmJsMain/       # Web-specific code
│   │   └── commonTest/       # Shared tests
│   └── build.gradle.kts      # Module build configuration
├── iosApp/                   # iOS application entry point
├── gradle/                   # Gradle wrapper
├── build.gradle.kts          # Root build configuration
├── settings.gradle.kts       # Project settings
└── README.md                 # This file
```

---

## 🚀 Getting Started

### Prerequisites
- **JDK 11** or higher
- **Android Studio Hedgehog** (2023.1.1) or newer
- **Xcode 15+** (for iOS development on macOS)
- **Kotlin 2.0.20** or higher

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/kyrkCZ/Portfolio.git
   cd Portfolio
   ```

2. **Open in Android Studio**
   - Launch Android Studio
   - Select "Open" and navigate to the cloned directory
   - Wait for Gradle sync to complete

### Running the Application

#### Android
```bash
./gradlew :composeApp:installDebug
```
Or use the "Run" button in Android Studio with an Android device/emulator.

#### iOS
1. Open `iosApp/iosApp.xcodeproj` in Xcode
2. Select your target device or simulator
3. Click "Run" (⌘R)

#### Desktop
```bash
./gradlew :composeApp:run
```

#### Web (WebAssembly)
```bash
./gradlew :composeApp:wasmJsBrowserDevelopmentRun
```
The application will be available at `http://localhost:8080`

---

## 🎨 Features in Detail

### Cryptography Suite
The application includes educational implementations of various cryptographic algorithms:

#### ADFGVX Cipher
- **Historical Context**: Used by German forces in WWI
- **Features**: Polybius square with transposition
- **Supports**: Standard and extended alphabets

#### Affine Cipher
- **Mathematical Foundation**: Linear algebra-based substitution
- **Features**: Customizable coefficients, supports alphanumeric text
- **Educational**: Demonstrates modular arithmetic

#### Playfair Cipher
- **Interactive Matrix**: Visual representation of the 5×5 key matrix
- **Features**: Digraph encryption with automatic padding
- **Language Support**: English and Czech alphabets
- **Key Validation**: Ensures key uniqueness and minimum length

### NASA APOD Integration
- Fetches daily astronomy pictures from NASA's API
- Displays high-quality space imagery
- Provides educational descriptions and metadata
- Handles loading states and errors gracefully

---

## 🔧 Configuration

### API Keys
To use the NASA APOD feature, you need a NASA API key:

1. Get your free API key from [NASA API Portal](https://api.nasa.gov/)
2. Replace the API key in `App.kt`:
   ```kotlin
   "NASA" -> NasaScreen("YOUR_API_KEY_HERE")
   ```

### Build Configuration
- Minimum Android SDK: 24
- Target Android SDK: 35
- Compile SDK: 35
- iOS Deployment Target: 14.0

---

## 🧪 Testing

Run unit tests:
```bash
./gradlew test
```

Run Android instrumented tests:
```bash
./gradlew connectedAndroidTest
```

---

## 📱 Screenshots

*(Screenshots would be added here showing the application running on different platforms)*

---

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request. For major changes:

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

---

## 👤 Author

**Jakub Kramný**
- Email: jakubkramny93@gmail.com
- GitHub: [@kyrkCZ](https://github.com/kyrkCZ)

---

## 🙏 Acknowledgments

- [JetBrains](https://www.jetbrains.com/) for Kotlin and Compose Multiplatform
- [NASA](https://api.nasa.gov/) for the APOD API
- The open-source community for various libraries and tools

---

## 📚 Learn More

- [Kotlin Multiplatform Documentation](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)
- [Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform)
- [Kotlin/Wasm](https://kotlinlang.org/docs/wasm-overview.html)
- [Ktor Client Documentation](https://ktor.io/docs/getting-started-ktor-client.html)

---

<div align="center">

**⭐ Star this repository if you find it helpful!**

Made with ❤️ using Kotlin Multiplatform

</div>
