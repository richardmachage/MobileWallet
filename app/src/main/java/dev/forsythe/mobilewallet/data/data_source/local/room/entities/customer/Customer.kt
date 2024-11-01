package dev.forsythe.mobilewallet.data.data_source.local.room.entities.customer

import android.accounts.Account
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.forsythe.mobilewallet.domain.models.CustomerModel

@Entity(tableName = "customer_tbl")
data class Customer(
    @PrimaryKey(autoGenerate = true)
    val id : Int =0,
    val customerId : String,
    val firstName : String,
    val lastName : String,
    val account: String,
    val email : String
){
    fun toDomainModel():CustomerModel{
        return CustomerModel(
            firstName= firstName,
            lastName = lastName,
            id = customerId,
            account=account,
            email=email
        )
    }
}
