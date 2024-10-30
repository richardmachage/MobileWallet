package dev.forsythe.mobilewallet.presentation.components.buttons

import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun OutlineButtonWallet(
    modifier: Modifier = Modifier,
    onClick : () -> Unit,
    text : String

){
    OutlinedButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Text(text = text)
    }
}