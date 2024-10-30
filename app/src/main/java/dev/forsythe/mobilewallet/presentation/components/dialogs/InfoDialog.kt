package dev.forsythe.nisave.common.ui.components.dialogs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.forsythe.mobilewallet.R
import dev.forsythe.mobilewallet.presentation.components.buttons.OutlineButtonWallet
import dev.forsythe.mobilewallet.presentation.components.texts.BoldText


@Composable
fun InfoDialog(
    showDialog: Boolean,
    title: String,
    message: String,
    onConfirm: () -> Unit,
) {

    if (showDialog) {
        AlertDialog(
            onDismissRequest = {  },
            title = {
                BoldText(text = title, fontSize = 22.sp)
            },
            text = {
                Box {
                    Text(text = message)
                }
            },
            confirmButton = {
                OutlineButtonWallet(text = stringResource(id = R.string.dismiss_dialog_btn)
                , onClick = {
                    onConfirm()
                    }
                )

            }
        )
    }
}