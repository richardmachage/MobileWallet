package dev.forsythe.mobilewallet.data.data_source.remote.network.model.request

import kotlinx.serialization.Serializable

@Serializable
data class SendMoneyRequest(
    val accountFrom: String,
    val accountTo: String,
    val amount: Int,
    val customerId: String
)