package dev.forsythe.mobilewallet.domain.repository

import dev.forsythe.mobilewallet.domain.models.CustomerModel
import kotlinx.coroutines.flow.Flow

interface CustomerRepo {

    suspend fun logIn(customerId:String, pin : String) : Result<CustomerModel>

    suspend fun logOut(customerId: String) : Result<Boolean>

    suspend fun getUserDetails(): Result<Flow<CustomerModel>>

}