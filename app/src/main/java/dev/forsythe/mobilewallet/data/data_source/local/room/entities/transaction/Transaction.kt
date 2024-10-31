package dev.forsythe.mobilewallet.data.data_source.local.room.entities.transaction

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.forsythe.mobilewallet.domain.models.TransactionModel

@Entity(tableName = "transactions_tbl")
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id : Int=0,
    val customerId : String,
    val accountNo : String,
    val accountTo : String,
    val amount : Double,
    val status : String,
    val timestamp : Long = System.currentTimeMillis()
){
    fun toDomainModel():TransactionModel{
        return TransactionModel(
            id = id,
            amount = amount,
            status = status,
            timestamp = timestamp,
            accountTo = accountTo,
        )
    }
}
