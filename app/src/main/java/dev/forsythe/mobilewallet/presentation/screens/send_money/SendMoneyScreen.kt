package dev.forsythe.mobilewallet.presentation.screens.send_money

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dev.forsythe.mobilewallet.R
import dev.forsythe.mobilewallet.presentation.components.CircularProgressIndicatorWallet
import dev.forsythe.mobilewallet.presentation.components.InputField
import dev.forsythe.mobilewallet.presentation.components.buttons.BackButton
import dev.forsythe.mobilewallet.presentation.components.buttons.FilledButtonWallet
import dev.forsythe.mobilewallet.presentation.components.dialogs.ConfirmDialog
import dev.forsythe.mobilewallet.presentation.components.spacers.VerticalSpacer
import dev.forsythe.mobilewallet.presentation.components.texts.BoldText
import dev.forsythe.nisave.common.ui.components.dialogs.InfoDialog


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SendMoneyScreen(
    navController: NavController
) {
    val sendMoneyViewModel = hiltViewModel<SendMoneyViewModel>()
    val context = LocalContext.current
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    CircularProgressIndicatorWallet(
        isLoading = sendMoneyViewModel.sendMoneyScreenState.isLoading,
        displayText = "Sending Money..."
    )

    InfoDialog(
        showDialog = sendMoneyViewModel.sendMoneyScreenState.infoDialogMessage != null,
        title = sendMoneyViewModel.sendMoneyScreenState.dialogTittle?:"",
        message = sendMoneyViewModel.sendMoneyScreenState.infoDialogMessage?:"",
        onConfirm = { sendMoneyViewModel.resetDialogs()}
    )
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.TopCenter
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LargeTopAppBar(
                        title = { BoldText(text = "Send Money") },
                        navigationIcon = {
                            BackButton(onClick = { navController.navigateUp() })
                        },
                        scrollBehavior = scrollBehavior
                    )
                    /*AnimatedVisibility(
                        scrollBehavior.state.collapsedFraction < 0.5
                    ) {

                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Box {
                                CircularImage(
                                    image = painterResource(R.mipmap.blank_profile),
                                    height = 200,
                                    width = 200,
                                    contentScale = ContentScale.Crop
                                )

                                IconButton(
                                    modifier = Modifier
                                        .yOffset(5)
                                        .xOffset(10)
                                        .align(
                                            Alignment.BottomEnd
                                        ),
                                    onClick = {
                                        //TODO
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Edit,
                                        contentDescription = "edit",
                                    )
                                }
                            }
                        }
                    }*/
                }
            }
        }
    ) {paddingValues->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState()),
            contentAlignment = Alignment.Center
        ) {

            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
                    .imePadding(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                //input account to send money to
                var accountTo by remember { mutableStateOf("") }
                InputField(
                    modifier = Modifier.fillMaxWidth(0.9f),
                    text = accountTo,
                    label = "Account you're sending money to",
                    onValueChange = {text->
                        accountTo = text.trim()
                    }
                )
                VerticalSpacer(10)
                //input amount to send
                var amount by remember { mutableStateOf("") }
                InputField(
                    modifier = Modifier.fillMaxWidth(0.9f),
                    text = amount,
                    label = "Amount to send",
                    onValueChange = {text->
                        amount = text.trim()
                    },
                    keyboardType = KeyboardType.Number

                )

                //confirm send money dialog
                var showSendMoneyDialog by remember { mutableStateOf(false) }
                ConfirmDialog(
                    showDialog = showSendMoneyDialog,
                    title = sendMoneyViewModel.sendMoneyScreenState.dialogTittle?:"",
                    message = sendMoneyViewModel.sendMoneyScreenState.confirmDialogMessage?:"",
                    onConfirm = {
                        val intAmount = amount.toIntOrNull()
                            if (intAmount != null) {
                                sendMoneyViewModel.onSendMoney(accountTo=accountTo, amount = intAmount)
                                showSendMoneyDialog = false
                                amount = ""
                                accountTo = ""
                            }
                    },
                    onDismiss = {
                        showSendMoneyDialog = false
                        sendMoneyViewModel.resetDialogs()
                        amount = ""
                        accountTo = ""
                    }
                )
                VerticalSpacer(20)
                //send money button
                FilledButtonWallet(
                    onClick = {
                        showSendMoneyDialog = true
                        sendMoneyViewModel.showConfirmDialog(title = "Confirm Send Money", message = "Confirm sending of KSH $amount to $accountTo" )
                    },
                    text = stringResource(R.string.send_money_lbl),
                    enabled = amount.isNotBlank() && accountTo.isNotBlank()
                )
            }
        }
    }

}
