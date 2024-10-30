package dev.forsythe.mobilewallet.presentation.components.dialogs

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import dev.forsythe.mobilewallet.R
import dev.forsythe.mobilewallet.presentation.components.buttons.FilledButtonWallet
import dev.forsythe.mobilewallet.presentation.components.buttons.OutlineButtonWallet
import dev.forsythe.mobilewallet.presentation.components.texts.BoldText

@Composable
fun ConfirmDialog(
    showDialog: Boolean,
    title: String,
    message: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    icon: @Composable (() -> Unit)? = null
) {

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            title = {
                BoldText(text = title, fontSize = 22.sp)
            },
            text = {
                Box {
                    Text(text = message)
                }
            },
            icon = icon,
            confirmButton = {
                OutlineButtonWallet(
                    text = stringResource(id = R.string.ok_btn),
                    onClick = {
                        onConfirm()
                    }
                )
            },
            dismissButton = {
                FilledButtonWallet(
                    text = stringResource(id = R.string.cancel_btn),
                    onClick = {
                        onDismiss()
                    }
                )
            },

        )
    }
}
