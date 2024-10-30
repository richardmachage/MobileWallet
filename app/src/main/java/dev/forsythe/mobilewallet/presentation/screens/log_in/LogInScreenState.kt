package dev.forsythe.mobilewallet.presentation.screens.log_in

import androidx.paging.LoadState

data class LogInScreenState(
    var isLoading: Boolean = false,
    var toastMessage : String? = null,
    var showInfoDialog : Boolean = false,
    var navigateToHome : Boolean = false,
)
