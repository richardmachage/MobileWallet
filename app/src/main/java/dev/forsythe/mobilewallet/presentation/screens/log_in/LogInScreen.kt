package dev.forsythe.mobilewallet.presentation.screens.log_in

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavController
import dev.forsythe.mobilewallet.R
import dev.forsythe.mobilewallet.presentation.components.InputField
import dev.forsythe.mobilewallet.presentation.components.buttons.BackButton
import dev.forsythe.mobilewallet.presentation.components.buttons.FilledButtonWallet
import dev.forsythe.mobilewallet.presentation.components.spacers.VerticalSpacer
import dev.forsythe.mobilewallet.presentation.components.texts.BoldText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogInScreen(
    navController: NavController
){
    val context = LocalContext.current
    Scaffold(

    ) {
        Box (
            modifier = Modifier.fillMaxSize().padding(it),
            contentAlignment = Alignment.Center
        ){
            BoldText(
                modifier = Modifier.align(Alignment.TopCenter),
                text = stringResource(R.string.log_in_btn)
            )
            Column {
                //input id
                InputField(
                    text = "",
                    label = stringResource(R.string.customer_id_lbl),
                )
                //input pin
                InputField(
                    text = "",
                    label = stringResource(R.string.pin_lbl),
                    keyboardType = KeyboardType.Number
                )

                VerticalSpacer(20)
                //log in button
                FilledButtonWallet(
                    onClick = {
                    },
                    text = stringResource(R.string.log_in_btn)
                )
            }
        }
    }

}