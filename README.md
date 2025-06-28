
Firebase Notification Demo App
==============================

This demo Android app integrates Firebase Cloud Messaging (FCM) with Jetpack Compose to showcase:

- Push notifications
- Redirection to specific screens based on payload
- In-app form to send notifications to all devices
- Bottom navigation with 4 tabs

Features
--------

- 🔔 Receive push notifications using FCM
- 📬 Show notifications in the system tray
- 🔀 Navigate to Home, Favorite, Cart, or Profile screen on notification tap
- 📝 In-app UI to send notification (title, body, type)
- 📡 Broadcast to all devices via topic `all_devices`
- ☀️ Light theme only
- ✅ Compatible with Android 7 to Android 14+

Project Structure
-----------------

- MainActivity.kt                // Handles notification redirection and navigation
- FCMService.kt                 // Receives and displays push notifications
- SendNotificationActivity.kt   // UI to compose and send FCM message
- GoogleAuthTokenProvider.kt    // Builds and sends JWT auth request to FCM
- FcmServiceInterface.kt        // Retrofit interface for FCM v1 API
- res/raw/service_account.json  // Your Firebase service account (see below)

Setup Instructions
------------------

1. Clone the Project

    git clone https://github.com/your-username/fcm-notification-demo.git
    cd fcm-notification-demo

2. Firebase Configuration

✅ Add `google-services.json`
- Go to https://console.firebase.google.com/
- Create a new project (or use existing)
- Register your Android app
- Download `google-services.json` and place it inside the `app/` directory

3. Add Firebase Service Account

- In Firebase Console > ⚙️ Project Settings > Service Accounts
- Click "Generate new private key"
- Place the downloaded JSON file into `res/raw/` and rename it to `service_account.json`

⚠️ Important: Do not share or commit this file in public repositories.

4. Update Your Firebase Project ID

In `FcmServiceInterface.kt`, replace the hardcoded project ID with your actual Firebase project ID:

    @POST("v1/projects/YOUR_PROJECT_ID/messages:send")

Example:

    @POST("v1/projects/myapp-firebase-12345/messages:send")

5. Run the App

- Build the project in Android Studio
- Connect a real device or use an emulator
- On launch, the app subscribes to `all_devices` topic
- Fill in title, body, and type (`home`, `favorite`, `cart`, or `profile`)
- Tap "Send" — all devices receive the push notification

Android 13+ Permission
----------------------

For Android 13 and above, the app requests `POST_NOTIFICATIONS` permission at runtime.

Notification Behavior
---------------------

| Payload `type` | Opens Screen |
|----------------|--------------|
| home           | Home tab     |
| favorite       | Favorite tab |
| cart           | Cart tab     |
| profile        | Profile tab  |


APK - [fcm_demo.zip](https://github.com/user-attachments/files/20959897/fcm_demo.zip)
