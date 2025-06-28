package com.pycreations.fcmdemo.notifications
import android.content.Context
import android.util.Log
import retrofit2.Callback

suspend fun sendFcmNotification(context: Context, title: String, body: String, type: String) {
    val authToken = GoogleAuthTokenProvider(context).getAccessToken()

    val notification = FcmMessage(
        message = Message(
            topic = "all_devices",
            notification = Notification(title, body),
            data = mapOf("type" to type),
        )
    )

    val call = getFcmService().sendNotification("Bearer $authToken", notification)
    call.enqueue(object : Callback<Void> {
        override fun onResponse(
            call: retrofit2.Call<Void?>,
            response: retrofit2.Response<Void?>
        ) {
            if (response.isSuccessful) {
                Log.d("Notification_Status", "Notification sent successfully!")
            } else {
                Log.e(
                    "Notification_Status",
                    "Error: ${response.code()} - ${response.errorBody()?.string()}"
                )
            }
        }

        override fun onFailure(call: retrofit2.Call<Void?>, t: Throwable) {
            Log.e("Notification_Status", "Failure: ${t.message}", t)
        }
    })
}
