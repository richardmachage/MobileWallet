package dev.forsythe.mobilewallet.data.data_source.remote.network.model.response

import kotlinx.serialization.Serializable

@Serializable
data class BalanceResponse(
    val accountNo: String,
    val balance: Double
)