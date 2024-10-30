package dev.forsythe.mobilewallet.network.model.request

import kotlinx.serialization.Serializable

@Serializable
data class BalanceRequest(
    val accountNo: String,
    val customerId: String
)