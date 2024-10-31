package dev.forsythe.mobilewallet.presentation.screens.log_in

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dev.forsythe.mobilewallet.R
import dev.forsythe.mobilewallet.presentation.components.CircularProgressIndicatorWallet
import dev.forsythe.mobilewallet.presentation.components.InputField
import dev.forsythe.mobilewallet.presentation.components.PasswordInputField
import dev.forsythe.mobilewallet.presentation.components.buttons.FilledButtonWallet
import dev.forsythe.mobilewallet.presentation.components.spacers.VerticalSpacer
import dev.forsythe.mobilewallet.presentation.components.texts.BoldText
import dev.forsythe.mobilewallet.presentation.navigation.MobileWalletRoutes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogInScreen(
    navController: NavController
){
    val logInViewModel = hiltViewModel<LogInViewModel>()
    val context = LocalContext.current

    LaunchedEffect(logInViewModel.logInScreenState.toastMessage) {
        val message = logInViewModel.logInScreenState.toastMessage
        message?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            logInViewModel.resetToast()
        }

    }

    LaunchedEffect(logInViewModel.logInScreenState.navigateToHome) {
        val doNavigate = logInViewModel.logInScreenState.navigateToHome
        if (doNavigate){
            navController.navigate(route = MobileWalletRoutes.HOME_SCREEN.name){
                popUpTo(MobileWalletRoutes.LOG_IN_SCREEN.name){inclusive = true}
            }
        }
    }

    CircularProgressIndicatorWallet(
        isLoading = logInViewModel.logInScreenState.isLoading,
        displayText = stringResource(R.string.logging_in_progress_text)
    )
    Scaffold(

    ) {
        Box (
            modifier = Modifier.fillMaxSize().padding(it),
            contentAlignment = Alignment.Center
        ){


            BoldText(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 50.dp),
                text = stringResource(R.string.welcome_log_in),
                fontSize = 32.sp,
            )

            Column(
                modifier = Modifier.fillMaxWidth().padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                BoldText(
                    modifier = Modifier
                        //.align(Alignment.TopCenter)
                        .padding(top = 50.dp),
                    text = "Log in to proceed...",
                    fontSize = 22.sp,
                )

                //input id
                var customerId by remember { mutableStateOf("") }
                InputField(
                    modifier = Modifier.fillMaxWidth(),
                    text = customerId,
                    label = stringResource(R.string.customer_id_lbl),
                    onValueChange = {text->
                        customerId = text.trim()
                    }
                )
                //input pin
                var pin by remember { mutableStateOf("") }

                PasswordInputField(
                    modifier = Modifier.fillMaxWidth(),
                    password = pin,
                    onPasswordChange = {text->
                        pin = text.trim()
                    }
                )

                VerticalSpacer(20)
                //log in button
                FilledButtonWallet(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        logInViewModel.onLogIn(customerId,pin)
                    },
                    text = stringResource(R.string.log_in_btn),
                    enabled = customerId.isNotBlank() && pin.isNotBlank()
                )
            }
        }
    }

}