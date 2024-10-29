package dev.forsythe.mobilewallet.network.model.request

data class LogInRequest(
    val customerId: String,
    val pin: String
)