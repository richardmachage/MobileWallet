package dev.forsythe.mobilewallet.data.data_source.local.room.entities.transaction

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions_tbl")
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id : Int=0,
    val customerId : String,
    val accountNo : String,
    val amount : Int,
    val status : String,
    val timestamp : Long
)
