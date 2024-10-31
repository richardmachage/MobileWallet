package dev.forsythe.mobilewallet.presentation.screens.send_money

data class SendMoneyScreenState(
    var isLoading : Boolean = false,
    var toastMessage : String? = null,
    var infoDialogMessage : String? = null,
    var confirmDialogMessage : String? = null,
    var dialogTittle : String? = null,
)
