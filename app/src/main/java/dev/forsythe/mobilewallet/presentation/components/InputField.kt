package dev.forsythe.mobilewallet.presentation.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import dev.forsythe.mobilewallet.presentation.components.texts.BoldText

@Composable
fun InputField(
    text: String = "",
    prefix: String? = null,
    keyboardType: KeyboardType = KeyboardType.Unspecified,
    onValueChange: (String) -> Unit = {}
) {
    OutlinedTextField(
        prefix = {
            prefix?.let {
                BoldText(text = prefix)
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        value = text,
        onValueChange = {
            onValueChange(it)
        },
        shape = RoundedCornerShape(10),
    )
}