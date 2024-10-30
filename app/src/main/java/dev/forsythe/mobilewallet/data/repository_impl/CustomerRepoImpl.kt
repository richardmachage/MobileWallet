package dev.forsythe.mobilewallet.data.repository_impl

import dev.forsythe.mobilewallet.data.data_source.local.preferences.PreferenceStore
import dev.forsythe.mobilewallet.data.data_source.local.room.entities.customer.CustomerDao
import dev.forsythe.mobilewallet.domain.models.CustomerModel
import dev.forsythe.mobilewallet.domain.repository.CustomerRepo
import dev.forsythe.mobilewallet.data.data_source.remote.network.client.KtorClient
import dev.forsythe.mobilewallet.data.data_source.remote.network.client.customerLogIn
import dev.forsythe.mobilewallet.data.data_source.remote.network.client.getUserAccount
import dev.forsythe.mobilewallet.data.data_source.remote.network.model.response.AccountResponse
import dev.forsythe.mobilewallet.data.data_source.remote.network.model.response.LogInResponse
import dev.forsythe.mobilewallet.utils.LOG_IN_STATE
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CustomerRepoImpl @Inject constructor(
    private val ktorClient: KtorClient,
    private val customerDao: CustomerDao,
    private val preferenceStore: PreferenceStore,
) : CustomerRepo {
    override suspend fun logIn(customerId: String, pin: String): Result<CustomerModel> {
        //perform call to api
        //return result based on response

        return try {
            val result = ktorClient.customerLogIn(customerId, pin)
            val accountResult = ktorClient.getUserAccount(customerId)
            if (result.statusCode == HttpStatusCode.OK && accountResult.statusCode == HttpStatusCode.OK) {
                val response = result.body as LogInResponse
                val accountNo = (accountResult.body as AccountResponse).accountNo
                val customer = CustomerModel(
                    name = response.firstName + "" + response.lastName,
                    id = response.customerId,
                    email = response.email,
                    account = accountNo
                )

                //add the customer to the database
                customerDao.addCustomer(customer.toDataModel())

                //update log in status
                preferenceStore.saveData(LOG_IN_STATE, "true")


                Result.success(customer)
            } else {

                Result.failure(Throwable(message = "Failed to log in try again later"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }

    }

    override suspend fun logOut(customerId: String): Result<Boolean> {
        //delete user data from db
        //update log in state
        //log out
        return try {
            customerDao.deleteCustomer(customerId = customerId)

            preferenceStore.deleteData(LOG_IN_STATE)

            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getUserDetails(): Result<Flow<CustomerModel>> {
        return try {
            Result.success(customerDao.getCustomerDetails().map { it.toDomainModel() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}