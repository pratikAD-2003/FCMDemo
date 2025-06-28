package com.pycreations.fcmdemo.notifications

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface FcmServiceInterface {
    @POST("v1/projects/evolveskillpath-64c9d/messages:send")
    fun sendNotification(
        @Header("Authorization") authToken: String,
        @Body notification: FcmMessage
    ): Call<Void>
}

data class FcmMessage(
    val message: Message
)

data class Message(
    val topic: String,
    val notification: Notification,
    val data: Map<String, String>
)

data class Notification(
    val title: String,
    val body: String
)

fun getFcmService(): FcmServiceInterface {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://fcm.googleapis.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(FcmServiceInterface::class.java)
}
