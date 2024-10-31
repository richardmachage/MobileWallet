package dev.forsythe.mobilewallet.presentation.screens.statements

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.forsythe.mobilewallet.data.data_source.TransactionStatus
import dev.forsythe.mobilewallet.data.data_source.local.preferences.PreferenceStore
import dev.forsythe.mobilewallet.data.data_source.remote.network.model.response.TransactionResponse
import dev.forsythe.mobilewallet.domain.models.TransactionModel
import dev.forsythe.mobilewallet.domain.repository.CustomerRepo
import dev.forsythe.mobilewallet.domain.repository.TransactionsRepo
import dev.forsythe.mobilewallet.utils.CUSTOMER_ID
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StatementViewModel @Inject constructor(
    private val transactionsRepo: TransactionsRepo,
    private val customerRepo: CustomerRepo,
    private val preferenceStore: PreferenceStore,
): ViewModel() {
    private lateinit var customerId : String
    init {
         preferenceStore.loadData(CUSTOMER_ID)?.let {
             customerId = it
         }
    }
    private var _statementScreenState by mutableStateOf(StatementScreenState())
    val statementScreenState : StatementScreenState
        get() = _statementScreenState

    private val _records = MutableStateFlow<List<TransactionResponse>>(emptyList())
    val records : StateFlow<List<TransactionResponse>> = _records


    fun getAllRecords(){
        viewModelScope.launch {
            val result = transactionsRepo.getMiniStatement(customerId)

            result.onSuccess {
                _records.value = it
            }

            result.onFailure {
                showToast(message = it.message.toString())
            }
        }
    }

    private fun showToast(message:String){
        _statementScreenState = _statementScreenState.copy(toastMessage = message)
    }

    fun resetToast(){
        _statementScreenState = _statementScreenState.copy(toastMessage = null)
    }

    fun isLoadingState(value : Boolean){
        _statementScreenState = _statementScreenState.copy(isLoading = value )
    }
}