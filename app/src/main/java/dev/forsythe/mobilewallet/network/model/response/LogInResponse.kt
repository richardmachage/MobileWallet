package dev.forsythe.mobilewallet.network.model.response

data class LogInResponse(
    val customerId: String,
    val email: String,
    val firstName: String,
    val lastName: String
)