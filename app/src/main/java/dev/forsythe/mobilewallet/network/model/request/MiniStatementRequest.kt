package dev.forsythe.mobilewallet.network.model.request

import kotlinx.serialization.Serializable

@Serializable
data class MiniStatementRequest(
    val accountNo: String,
    val customerId: String
)