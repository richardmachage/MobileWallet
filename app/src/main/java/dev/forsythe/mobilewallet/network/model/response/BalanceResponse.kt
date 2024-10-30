package dev.forsythe.mobilewallet.network.model.response

data class BalanceResponse(
    val accountNo: String,
    val balance: Double
)