package dev.forsythe.mobilewallet.domain.repository

import androidx.paging.PagingData
import androidx.paging.PagingSource
import dev.forsythe.mobilewallet.data.data_source.local.room.entities.transaction.Transaction
import dev.forsythe.mobilewallet.data.data_source.remote.network.model.request.SendMoneyRequest
import dev.forsythe.mobilewallet.data.data_source.remote.network.model.response.TransactionResponse
import dev.forsythe.mobilewallet.domain.models.TransactionModel
import kotlinx.coroutines.flow.Flow

interface TransactionsRepo {
    //suspend fun getLastTransactions() : Flow<List<TransactionModel>>
    fun getLastTransactions() : PagingSource<Int,Transaction>

    suspend fun getMiniStatement(customerId : String) : Result<List<TransactionResponse>>

    suspend fun sendMoney(sendMoneyRequest: SendMoneyRequest) : Result<String>

    suspend fun checkAccountBalance(accountNo: String, customerId: String) : Result<String>
}