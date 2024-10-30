package dev.forsythe.mobilewallet.domain.models

import dev.forsythe.mobilewallet.data.data_source.local.room.entities.customer.Customer

data class CustomerModel(
    val name : String,
    val id : String,
    val account : String,
    val email : String
){
    fun toDataModel():Customer{
        return Customer(
            customerId = id,
            name = name,
            account = account,
            email = email
        )
    }
}
