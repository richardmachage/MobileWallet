package dev.forsythe.mobilewallet.domain.repository

import dev.forsythe.mobilewallet.domain.models.CustomerModel
import kotlinx.coroutines.flow.Flow

interface CustomerRepo {

    suspend fun logIn(customerId:String, pin : String) : Result<CustomerModel>

    suspend fun logOut() : Result<Boolean>

    fun getUserDetails(): Flow<CustomerModel>

}