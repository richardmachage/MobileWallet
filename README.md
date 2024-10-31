# Mobile Wallet

Mobile Wallet is an Android app simulating a digital wallet, leveraging modern Android development tools and practices.

## Tech Stack
- **Kotlin**
- **Room** (for local database)
- **Shared Preferences** (for key-value storage)
- **Jetpack Compose** (UI toolkit)
- **Jetpack Navigation** (navigation framework)
- **Ktor Client** (HTTP client for API interactions)
- **Hilt** (for dependency injection)

## Prerequisites
- **Android Studio**: Latest stable version (recommended)
- **JDK**: Version 17 or higher
- **Minum SDK**:  26 (Android 8)

## Setup Instructions

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/richardmachage/MobileWallet.git
bash```

2. **Environment Configuration**
Open the project in Android Studio and allow Gradle to sync, which will download the required dependencies.

3. **Set up the base URL for API connection:**
If testing on an emulator, configure BASE_URL to BASE_URL_EMULATOR in constants.kt.
Adjust the port in the URL according to the server’s running port.
  
For unit tests involving KtorClient in the *KtorClientIntegration.kt* , switch the URL from **BASE_URL_EMULATOR** to **BASE_URL** to ensure proper endpoints are used.

## Backend API
The server backed project is found here [MobileWalletAPI](https://github.com/richardmachage/MobileWalletSpringBootAPI)
