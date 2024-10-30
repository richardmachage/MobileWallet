package dev.forsythe.mobilewallet.data.data_source.remote.network.model.response

import kotlinx.serialization.Serializable

@Serializable
data class SendMoneyResponse(
    val response_message: String,
    val response_status: Boolean
)