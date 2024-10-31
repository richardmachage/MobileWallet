package dev.forsythe.mobilewallet.presentation.screens.last_transactions

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.forsythe.mobilewallet.domain.models.TransactionModel
import dev.forsythe.mobilewallet.domain.repository.TransactionsRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class LastTransactionsViewModel @Inject constructor(
    private val transactionsRepo: TransactionsRepo
) : ViewModel() {
    private var _lastTransactionScreenState by mutableStateOf(LastTransactionsScreenState())
    val lastTransactionsScreenState : LastTransactionsScreenState
        get() = _lastTransactionScreenState

    //Paged data for optimized reading from database
    fun getAllTransactions(): Flow<PagingData<TransactionModel>>{
        val pager = Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 40
            ),
            pagingSourceFactory = {transactionsRepo.getLastTransactions()}
        )

       return pager.flow.map { it.map {trans-> trans.toDomainModel() } }
    }

}