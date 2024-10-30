package dev.forsythe.mobilewallet.presentation.components.buttons

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BackButton(
    modifier: Modifier = Modifier,
    onClick : () -> Unit,
    color: Color = Color.Unspecified
){
    IconButton(
        modifier = modifier,
        onClick = { onClick() }) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Arrow Back",
            tint = color
        )
    }

}