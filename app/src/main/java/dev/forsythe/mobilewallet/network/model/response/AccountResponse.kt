package dev.forsythe.mobilewallet.network.model.response

import kotlinx.serialization.Serializable

@Serializable
data class AccountResponse(
    val accountNo: String,
    val balance: Double,
    val customerId: String,
    val id: Int
)