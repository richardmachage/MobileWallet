package dev.forsythe.mobilewallet.presentation.screens.log_in

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.forsythe.mobilewallet.domain.repository.CustomerRepo
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val customerRepo: CustomerRepo
): ViewModel() {

    private var _logInScreenState by mutableStateOf(LogInScreenState())
    val  logInScreenState : LogInScreenState
        get() = _logInScreenState

    private fun showToast(message:String){
        _logInScreenState = _logInScreenState.copy(toastMessage = message)
    }

    fun resetToast(){
        _logInScreenState = _logInScreenState.copy(toastMessage = null)
    }

    fun resetNav(){
        _logInScreenState = _logInScreenState.copy(navigateToHome = false)
    }
    fun onLogIn(
        customerId : String,
        pin : String
    ){
        viewModelScope.launch {
            _logInScreenState = _logInScreenState.copy(isLoading = true)
           val result =  customerRepo.logIn(customerId, pin)

            result.onSuccess {
                //navigate to home
                _logInScreenState = _logInScreenState.copy(isLoading = false)
                _logInScreenState = _logInScreenState.copy(navigateToHome = true)

            }

            result.onFailure {
                //show toast
                Log.e("LogIn", "onLogIn: ${it.message}", )
                _logInScreenState = _logInScreenState.copy(isLoading = false)
                showToast("Log in failed: ${it.message}")
            }
        }
    }
}