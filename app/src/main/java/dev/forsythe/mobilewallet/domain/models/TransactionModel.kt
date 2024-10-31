package dev.forsythe.mobilewallet.domain.models

import dev.forsythe.mobilewallet.data.data_source.local.room.entities.transaction.Transaction

data class TransactionModel(
    val id : Int,
    val accountTo : String,
    val amount : Double,
    val status : String,
    val timestamp : Long
)
