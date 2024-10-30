package dev.forsythe.mobilewallet.presentation.components.spacers

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HorizontalSpacer(width:Int){
    Spacer(modifier = Modifier.width(width.dp))
}