package dev.forsythe.mobilewallet.data.data_source.remote.network.model.request

import kotlinx.serialization.Serializable

@Serializable
data class MiniStatementRequest(
    val accountNo: String,
    val customerId: String
)