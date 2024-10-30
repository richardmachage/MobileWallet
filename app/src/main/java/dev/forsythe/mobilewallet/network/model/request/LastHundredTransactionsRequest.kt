package dev.forsythe.mobilewallet.network.model.request

import kotlinx.serialization.Serializable

@Serializable
data class LastHundredTransactionsRequest(
    val customerId: String
)