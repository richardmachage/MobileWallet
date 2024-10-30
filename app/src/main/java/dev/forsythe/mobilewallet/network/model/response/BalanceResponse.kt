package dev.forsythe.mobilewallet.network.model.response

import kotlinx.serialization.Serializable

@Serializable
data class BalanceResponse(
    val accountNo: String,
    val balance: Double
)