package dev.forsythe.mobilewallet.data.data_source.remote.network.model.request

import kotlinx.serialization.Serializable

@Serializable
data class LogInRequest(
    val customerId: String,
    val pin: String
)