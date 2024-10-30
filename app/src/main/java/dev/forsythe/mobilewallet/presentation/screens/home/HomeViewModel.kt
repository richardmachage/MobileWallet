package dev.forsythe.mobilewallet.presentation.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.app.NotificationCompat.MessagingStyle.Message
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.forsythe.mobilewallet.domain.repository.CustomerRepo
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val customerRepo: CustomerRepo
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
}