package dev.forsythe.mobilewallet.network.model.request

import kotlinx.serialization.Serializable

@Serializable
data class LogInRequest(
    val customerId: String,
    val pin: String
)