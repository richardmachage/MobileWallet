package dev.forsythe.mobilewallet.data.data_source.remote.network.model.response

import kotlinx.serialization.Serializable

@Serializable
data class LogInResponse(
    val customerId: String,
    val email: String,
    val firstName: String,
    val lastName: String
)