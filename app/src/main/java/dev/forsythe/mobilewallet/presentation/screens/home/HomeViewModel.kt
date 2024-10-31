package dev.forsythe.mobilewallet.presentation.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.app.NotificationCompat.MessagingStyle.Message
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.forsythe.mobilewallet.R
import dev.forsythe.mobilewallet.domain.models.CustomerModel
import dev.forsythe.mobilewallet.domain.repository.CustomerRepo
import dev.forsythe.mobilewallet.domain.repository.TransactionsRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val customerRepo: CustomerRepo,
    private val transactionsRepo: TransactionsRepo
) : ViewModel(){


    private var _homeScreenState by mutableStateOf(HomeScreenState())
    val homeScreenState : HomeScreenState
        get() = _homeScreenState


    fun showDialog(title:String, message : String){
        _homeScreenState = _homeScreenState.copy(dialogTitle = title, dialogInfo = message)
    }
    fun closeDialog() {
        _homeScreenState = _homeScreenState.copy(dialogInfo = null, dialogTitle = null)
    }
    fun resetNavigation(){
        _homeScreenState = _homeScreenState.copy(gotToLogIn = false)
    }
    fun onLogout(){
        viewModelScope.launch {
            _homeScreenState = _homeScreenState.copy(isLoading = true)
           val  result = customerRepo.logOut()

            result.onSuccess {
                //navigate to home
                _homeScreenState = _homeScreenState.copy(gotToLogIn = true, isLoading = false)
            }

            result.onFailure {
                showDialog(title = "Log out failed", message = "Error : ${it.message}")
                _homeScreenState = _homeScreenState.copy(isLoading = false)
            }

        }

    }

    fun onShowBalance(accountNo: String, customerId : String){
        viewModelScope.launch {
            _homeScreenState = _homeScreenState.copy(isLoading = true)
            val result = transactionsRepo.checkAccountBalance(accountNo = accountNo, customerId=customerId)

            result.onSuccess { balance->
                _homeScreenState = _homeScreenState.copy(dialogTitle = "Account Balance", dialogInfo = "The outstanding account balance is $balance", isLoading = false )
            }

            result.onFailure {throwable->
                _homeScreenState = _homeScreenState.copy(dialogTitle = "Account Balance", dialogInfo = "Sorry and error occurred :  ${throwable.message}", isLoading = false )
            }
        }
    }

    fun getCustomerDetails() : Flow<CustomerModel?>{
        return customerRepo.getUserDetails().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = null
        )
    }
}