package dev.forsythe.mobilewallet.presentation.screens.last_transactions.components

data class TransactionItem(
    val id : Int,
    val accountTo : String,
    val amount : String,
    val status : String
)
