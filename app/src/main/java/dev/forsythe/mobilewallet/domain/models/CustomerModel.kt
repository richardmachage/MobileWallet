package dev.forsythe.mobilewallet.domain.models

import dev.forsythe.mobilewallet.data.data_source.local.room.entities.customer.Customer

data class CustomerModel(
    val firstName : String,
    val lastName : String,
    val id : String,
    val account : String,
    val email : String
){
    fun toDataModel():Customer{
        return Customer(
            customerId = id,
            firstName = firstName,
            lastName = lastName,
            account = account,
            email = email
        )
    }
}
