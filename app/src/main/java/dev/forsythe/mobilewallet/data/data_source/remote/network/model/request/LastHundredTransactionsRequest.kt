package dev.forsythe.mobilewallet.data.data_source.remote.network.model.request

import kotlinx.serialization.Serializable

@Serializable
data class LastHundredTransactionsRequest(
    val customerId: String
)