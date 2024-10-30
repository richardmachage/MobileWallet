package dev.forsythe.mobilewallet.presentation.screens.home

import androidx.compose.ui.graphics.drawscope.Stroke

data class HomeScreenState(
    var isLoading : Boolean = false,
    var toastMessage : String? = null,
    var dialogInfo : String? = null,
    var dialogTitle : String? = null,
    var gotToLogIn : Boolean = false
)