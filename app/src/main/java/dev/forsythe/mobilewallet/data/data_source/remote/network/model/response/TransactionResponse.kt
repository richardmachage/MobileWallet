package dev.forsythe.mobilewallet.data.data_source.remote.network.model.response

import kotlinx.serialization.Serializable

@Serializable
data class TransactionResponse(
    val accountNo: String,
    val amount: Double,
    val balance: Double,
    val customerId: String,
    val debitOrCredit: String,
    val id: Int,
    val transactionId: String,
    val transactionType: String
)