package dev.forsythe.mobilewallet.presentation.components

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowInsetsControllerCompat

@Composable
fun SetSystemBarsColor (
    color: Color,
    isDarkIcons: Boolean
) {
    val window = (LocalContext.current as Activity).window
    val view = LocalView.current
    val insetsController = WindowInsetsControllerCompat(window, view)

    window.statusBarColor =  color.toArgb()
    window.navigationBarColor =  color.toArgb()
    insetsController.isAppearanceLightStatusBars = isDarkIcons
    insetsController.isAppearanceLightNavigationBars = isDarkIcons
}


@Composable
fun SetSystemBarsColor (
    statusBarColor : Color,
    navigationBarColor: Color,
    isStatusBarDarkIcons: Boolean,
    isNavBarDarkIcons : Boolean
) {
    val window = (LocalContext.current as Activity).window
    val view = LocalView.current
    val insetsController = WindowInsetsControllerCompat(window, view)

    window.statusBarColor = statusBarColor.toArgb()
    window.navigationBarColor = navigationBarColor.toArgb()
    insetsController.isAppearanceLightStatusBars = isStatusBarDarkIcons
    insetsController.isAppearanceLightNavigationBars = isNavBarDarkIcons
}