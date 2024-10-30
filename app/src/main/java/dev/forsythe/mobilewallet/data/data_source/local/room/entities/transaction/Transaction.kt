package dev.forsythe.mobilewallet.data.data_source.local.room.entities.transaction

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions_tbl")
data class Transaction(
    @PrimaryKey
    val id : Int,
    val account : String,
    val amount : Int
)
