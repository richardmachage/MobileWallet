package dev.forsythe.mobilewallet.presentation.components

import androidx.compose.foundation.layout.offset
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

fun Modifier.xOffset(xOffset: Int) : Modifier {
    return this.offset(x = (xOffset).dp)
}