package dev.forsythe.mobilewallet.presentation.components.dialogs

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import dev.forsythe.mobilewallet.R
import dev.forsythe.mobilewallet.presentation.components.buttons.FilledButtonWallet
import dev.forsythe.mobilewallet.presentation.components.buttons.OutlineButtonWallet

@Composable
fun InputDialog(
    showDialog: Boolean,
    confirmBtnName: String = stringResource(id = R.string.ok_btn),
    title: String,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    dialogContent: @Composable () -> Unit
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text(text = title, color = Color.White) },
            text = dialogContent,
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
                    text = stringResource(id =  R.string.cancel_btn),
                    onClick = {
                        onDismiss()
                    }
                )
            },
        )
    }
}
