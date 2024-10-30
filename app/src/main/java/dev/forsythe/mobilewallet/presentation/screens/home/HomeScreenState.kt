package dev.forsythe.mobilewallet.presentation.screens.home

import androidx.compose.ui.graphics.drawscope.Stroke

data class HomeScreenState(
    var isLoading : Boolean = false,
    var toastMessage : String? = null
)