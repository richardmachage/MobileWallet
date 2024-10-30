package dev.forsythe.mobilewallet.presentation.components.texts

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ClickableText(
    modifier: Modifier = Modifier,
    text : String,
    onClick : () -> Unit,
    fontWeight: FontWeight = FontWeight.Bold,
    textAlign: TextAlign = TextAlign.Unspecified,
    enabled: Boolean = true
){
    Text(
        modifier = modifier
            .clickable(
                enabled = enabled,
                onClick = {
                    onClick()
                }
            ),
        fontWeight = fontWeight,
        text = text,
        textAlign = textAlign,
    )

}