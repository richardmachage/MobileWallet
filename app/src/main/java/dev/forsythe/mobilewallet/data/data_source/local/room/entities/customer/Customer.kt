package dev.forsythe.mobilewallet.data.data_source.local.room.entities.customer

import android.accounts.Account
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customer_tbl")
data class Customer(
    @PrimaryKey
    val customerId : String,
    val name : String,
    val account: String,
    val email : String
)
