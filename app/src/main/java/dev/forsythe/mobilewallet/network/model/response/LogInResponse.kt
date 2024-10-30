package dev.forsythe.mobilewallet.network.model.response

import kotlinx.serialization.Serializable

@Serializable
data class LogInResponse(
    val customerId: String,
    val email: String,
    val firstName: String,
    val lastName: String
)