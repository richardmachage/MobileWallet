package dev.forsythe.mobilewallet.data.repository_impl

import dev.forsythe.mobilewallet.data.data_source.TransactionStatus
import dev.forsythe.mobilewallet.data.data_source.local.room.entities.customer.Customer
import dev.forsythe.mobilewallet.data.data_source.local.room.entities.customer.CustomerDao
import dev.forsythe.mobilewallet.data.data_source.local.room.entities.transaction.Transaction
import dev.forsythe.mobilewallet.data.data_source.local.room.entities.transaction.TransactionDao
import dev.forsythe.mobilewallet.data.data_source.remote.network.client.KtorClient
import dev.forsythe.mobilewallet.data.data_source.remote.network.client.accountBalance
import dev.forsythe.mobilewallet.data.data_source.remote.network.client.lastHundredTransactions
import dev.forsythe.mobilewallet.data.data_source.remote.network.client.sendMoney
import dev.forsythe.mobilewallet.data.data_source.remote.network.model.request.SendMoneyRequest
import dev.forsythe.mobilewallet.data.data_source.remote.network.model.response.BalanceResponse
import dev.forsythe.mobilewallet.data.data_source.remote.network.model.response.SendMoneyResponse
import dev.forsythe.mobilewallet.data.data_source.remote.network.model.response.TransactionResponse
import dev.forsythe.mobilewallet.domain.models.TransactionModel
import dev.forsythe.mobilewallet.domain.repository.TransactionsRepo
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class TransactionsRepoImpl @Inject constructor(
    private val ktorClient: KtorClient,
    private val transactionDao: TransactionDao,
    private val customerDao: CustomerDao
): TransactionsRepo {
    private lateinit var customer : Customer

    init {
        CoroutineScope(Dispatchers.IO).launch {
            customerDao.getCustomerDetails().collect{
                customer = it
            }
        }
    }


    override suspend fun getLastTransactions(): Flow<List<TransactionModel>> {
        //from room
       return transactionDao.getAllTransactions().map { list -> list.map { it.toDomainModel() } }
    }

    override suspend fun getMiniStatement(customerId: String): Result<List<TransactionResponse>> {
        return try {
            val result = ktorClient.lastHundredTransactions(customerId)
            if (result.statusCode == HttpStatusCode.OK){
                val response = result.body as List<TransactionResponse>
                Result.success(response)
            }
            else{
                val response = result.body as String
                Result.failure(Throwable(message = response))
            }
        }catch (e : Exception){
            Result.failure(e)
        }
    }

    override suspend fun sendMoney(sendMoneyRequest: SendMoneyRequest): Result<String> {
        //save to room
        val transactionId = transactionDao.addTransaction(
            Transaction(
                customerId = sendMoneyRequest.customerId,
                accountNo = sendMoneyRequest.accountFrom,
                accountTo = sendMoneyRequest.accountTo,
                amount = sendMoneyRequest.amount.toDouble(),
                status = TransactionStatus.PENDING.name,
            )
        )
        return  try {

            //send to remote
            val result = ktorClient.sendMoney(sendMoneyRequest=sendMoneyRequest)

            if (result.statusCode == HttpStatusCode.OK){
                //if successful update room
                transactionDao.updateTransactionStatus(status = TransactionStatus.SUCCEED.name, id = transactionId.toInt() )
                val response = result.body as SendMoneyResponse
                Result.success(response.response_message)
            }else{
                //failed also update status to failed
                transactionDao.updateTransactionStatus(status = TransactionStatus.FAILED.name, id = transactionId.toInt() )
                val response = result.body as String
                Result.failure(Throwable(message = response))
            }

        }
        catch (e : Exception){
            //failed also update status to failed
           // withContext(Dispatchers.IO){
                transactionDao.updateTransactionStatus(status = TransactionStatus.FAILED.name, id = transactionId.toInt() )
            //}
            Result.failure(e)
        }
    }

    override suspend fun checkAccountBalance(accountNo: String, customerId: String): Result<String> {
        return try {
           val result =  ktorClient.accountBalance(accountNo, customerId)
            if (result.statusCode == HttpStatusCode.OK){
                val response = result.body as BalanceResponse
                Result.success(response.balance.toString())
            }
            else{
                val response = result.body as String
                Result.failure(Throwable(message = response ))
            }
        }catch (e : Exception){
            Result.failure(e)
        }
    }
}