package dev.forsythe.mobilewallet.presentation.components.buttons

import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun FilledButtonWallet(
    modifier: Modifier = Modifier,
    onClick : () -> Unit,
    text : String
){
    FilledTonalButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Text(text = text)
    }
}