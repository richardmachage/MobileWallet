package dev.forsythe.mobilewallet.presentation.screens.send_money

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.forsythe.mobilewallet.data.data_source.remote.network.model.request.SendMoneyRequest
import dev.forsythe.mobilewallet.domain.models.CustomerModel
import dev.forsythe.mobilewallet.domain.repository.CustomerRepo
import dev.forsythe.mobilewallet.domain.repository.TransactionsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SendMoneyViewModel @Inject constructor(
    private val customerRepo: CustomerRepo,
    private val transactionsRepo: TransactionsRepo
) : ViewModel() {
    private lateinit var customer: CustomerModel

    init {
        viewModelScope.launch {
            customerRepo.getUserDetails().collect {
                customer = it
            }
        }
    }

    private var _sendMoneyScreenState by mutableStateOf(SendMoneyScreenState())
    val sendMoneyScreenState: SendMoneyScreenState
        get() = _sendMoneyScreenState


    fun onSendMoney(accountTo: String, amount: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _sendMoneyScreenState = _sendMoneyScreenState.copy(isLoading = true)
                val result = transactionsRepo.sendMoney(
                    SendMoneyRequest(
                        accountFrom = customer.account,
                        accountTo = accountTo,
                        amount = amount,
                        customerId = customer.id
                    )
                )

                result.onSuccess {
                    _sendMoneyScreenState = _sendMoneyScreenState.copy(isLoading = false)
                    showInfoDialog(title = "Transaction Report", message = it)
                }

                result.onFailure {
                    _sendMoneyScreenState = _sendMoneyScreenState.copy(isLoading = false)
                    showInfoDialog(title = "Failed Transaction", message = it.message.toString())
                }
            }
        }
    }


    fun showInfoDialog(title: String, message: String) {
        _sendMoneyScreenState =
            _sendMoneyScreenState.copy(dialogTittle = title, infoDialogMessage = message)
    }

    fun showConfirmDialog(title: String, message: String) {
        _sendMoneyScreenState =
            _sendMoneyScreenState.copy(dialogTittle = title, confirmDialogMessage = message)
    }

    fun resetDialogs() {
        _sendMoneyScreenState =
            _sendMoneyScreenState.copy(dialogTittle = null, confirmDialogMessage = null, infoDialogMessage = null)
    }
}