package dev.forsythe.mobilewallet.presentation.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation.Companion.keyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import dev.forsythe.mobilewallet.presentation.components.texts.BoldText

@Composable
fun InputField(
    modifier: Modifier = Modifier,
    text: String = "",
    label : String,
    prefix: String? = null,
    keyboardType: KeyboardType = KeyboardType.Unspecified,
    onValueChange: (String) -> Unit = {}
) {
    OutlinedTextField(
        modifier = modifier,
        prefix = {
            prefix?.let {
                BoldText(text = prefix)
            }
        },
        label = { Text(text = label) },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        value = text,
        onValueChange = {
            onValueChange(it)
        },
        shape = RoundedCornerShape(10),
    )
}