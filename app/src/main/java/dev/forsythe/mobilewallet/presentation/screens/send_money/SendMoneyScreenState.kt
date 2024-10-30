package dev.forsythe.mobilewallet.presentation.screens.send_money

data class SendMoneyScreenState(
    var isLoading : Boolean = false,
    var toastMessage : String? = null,
    var showInfoDialog : Boolean = false,
    var showConfirmDialog : Boolean = false
)
