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
## Sample Screenshots : Light
![logOutLight](https://github.com/user-attachments/assets/7281571e-4a57-4a5e-9f81-ada8245e778d) ![sendMoneyLight](https://github.com/user-attachments/assets/6fec06ee-98a0-4b54-83ff-5460d666cd76) ![transactionHistoryLight](https://github.com/user-attachments/assets/e22a0fce-97f7-4021-8f62-ebc87725582c)

## Sample Screenshots : Dark
![logIn](https://github.com/user-attachments/assets/12159070-3951-4e96-88fd-d38472d64d39) ![profile](https://github.com/user-attachments/assets/05e2f5ae-970a-4ab3-8e25-8bd4ef182cb2) ![sendMoneyDialog](https://github.com/user-attachments/assets/0824db55-336e-4fec-bbb9-7556738f1199)




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
