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
## Sample Screenshots
![logIn (1)](https://github.com/user-attachments/assets/e2ee37a8-4d78-4faa-8027-deba3cd32c96) ![logOut](https://github.com/user-attachments/assets/7117337c-d88e-489c-87ae-cc9e8bf4a4be) ![profile](https://github.com/user-attachments/assets/cf20946d-e544-44f1-971d-8b2e153e9ddb) ![home (4)](https://github.com/user-attachments/assets/34de89e6-55a1-4aad-adcf-74d9caf4253a) ![sendMoneyDialog](https://github.com/user-attachments/assets/3d802d95-7164-4581-ad49-5fe3dc50af9b) ![sendMoneyScreen](https://github.com/user-attachments/assets/fd63d20b-1490-4ef8-b4df-674c39ea6dbf)  ![statement](https://github.com/user-attachments/assets/722b1861-c4b0-41d1-8e5a-22dffe1c9706) ![transactionsHistory](https://github.com/user-attachments/assets/9961f2b8-4014-4b42-af40-6591a8afeb9c)












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
