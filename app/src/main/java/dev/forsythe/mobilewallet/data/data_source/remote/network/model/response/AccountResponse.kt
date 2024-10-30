package dev.forsythe.mobilewallet.data.data_source.remote.network.model.response

import kotlinx.serialization.Serializable

@Serializable
data class AccountResponse(
    val accountNo: String,
    val balance: Double,
    val customerId: String,
    val id: Int
)