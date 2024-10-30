package dev.forsythe.mobilewallet.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.forsythe.mobilewallet.data.data_source.local.preferences.PreferenceStore
import dev.forsythe.mobilewallet.presentation.navigation.MobileWalletRoutes
import dev.forsythe.mobilewallet.utils.LOG_IN_STATE
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val preferenceStore: PreferenceStore
) : ViewModel() {

    private fun isLoggedIn(): Boolean {
        val logInState = preferenceStore.loadData(LOG_IN_STATE)
        return logInState == "true"
    }

    fun setStartDestination(): String {
        return if (isLoggedIn()) {//logged in go to home
            MobileWalletRoutes.HOME_SCREEN.name
        } else { //not logged in, go to Log in
            MobileWalletRoutes.LOG_IN_SCREEN.name
        }
    }
}
