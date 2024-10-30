package dev.forsythe.mobilewallet.presentation.components.spacers

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun VerticalSpacer(height:Int){
    Spacer(modifier = Modifier.height(height.dp))
}