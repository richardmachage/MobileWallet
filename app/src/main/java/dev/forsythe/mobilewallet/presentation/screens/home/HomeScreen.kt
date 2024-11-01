package dev.forsythe.mobilewallet.presentation.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dev.forsythe.mobilewallet.R
import dev.forsythe.mobilewallet.presentation.components.CircularProgressIndicatorWallet
import dev.forsythe.mobilewallet.presentation.components.buttons.CardButton
import dev.forsythe.mobilewallet.presentation.components.dialogs.ConfirmDialog
import dev.forsythe.mobilewallet.presentation.components.spacers.VerticalSpacer
import dev.forsythe.mobilewallet.presentation.components.texts.BoldText
import dev.forsythe.mobilewallet.presentation.navigation.MobileWalletRoutes
import dev.forsythe.nisave.common.ui.components.dialogs.InfoDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController
){
    val homeViewModel = hiltViewModel<HomeViewModel>()
    val context = LocalContext.current
    val customer = homeViewModel.getCustomerDetails().collectAsState(null)
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    LaunchedEffect(homeViewModel.homeScreenState.gotToLogIn) {
        val goToLogin = homeViewModel.homeScreenState.gotToLogIn
        if (goToLogin){
            navController.navigate(route = MobileWalletRoutes.LOG_IN_SCREEN.name){
                popUpTo(MobileWalletRoutes.HOME_SCREEN.name){ inclusive = true}
            }
            homeViewModel.resetNavigation()
        }
    }

    CircularProgressIndicatorWallet(
        isLoading = homeViewModel.homeScreenState.isLoading,
        displayText = stringResource(R.string.loading)
    )

    //Log out dialog
    var showLogOutDialog by remember {   mutableStateOf(false)}
    ConfirmDialog(
        showDialog = showLogOutDialog,
        title = "Log Out",
        message = "Are you sure you want to log out?",
        onConfirm = {
            homeViewModel.onLogout()
            showLogOutDialog = false
        },
        onDismiss = {
            showLogOutDialog = false
        }
    )


    InfoDialog(
        showDialog = homeViewModel.homeScreenState.dialogInfo != null,
        message = homeViewModel.homeScreenState.dialogInfo?:"",
        title = homeViewModel.homeScreenState.dialogTitle?:"",
        onConfirm = {
            homeViewModel.closeDialog()
        }
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
                        title = { BoldText(text = stringResource(R.string.home_screen_tittle)) },
                        scrollBehavior = scrollBehavior
                    )
                    AnimatedVisibility(
                        scrollBehavior.state.collapsedFraction < 0.5
                    ) {
                        Column {
                            customer.value?.let {
                                BoldText(
                                    text = "Welcome, ${it.firstName}!",
                                    fontSize = 30.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    ) {innerPadding->

        Box (
            modifier = Modifier.padding(innerPadding).fillMaxSize().verticalScroll(
                rememberScrollState()
            ),
            contentAlignment = Alignment.Center
        ){

            Column (
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
               Row (
                   modifier = Modifier
                       .fillMaxWidth()
                       .padding(10.dp)
                   ,
                   verticalAlignment = Alignment.CenterVertically,
                   horizontalArrangement = Arrangement.SpaceEvenly
               ){
                   CardButton( //balance
                       onClick = {
                           customer.value?.let {
                               homeViewModel.onShowBalance(accountNo = it.account, customerId = it.id)
                           }
                          // homeViewModel.showDialog(title = "Account Balance", message = "You outstanding balance is KES 50000")
                       },
                       text = stringResource(R.string.balance_lbl)
                   )

                   CardButton(
                       onClick = {
                           navController.navigate(route = MobileWalletRoutes.SEND_MONEY_SCREEN.name)
                       },
                       text = stringResource(R.string.send_money_lbl)
                   )

               }

                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                    ,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ){
                    CardButton( //view statement
                        onClick = {
                            navController.navigate(route = MobileWalletRoutes.STATEMENTS_SCREEN.name)
                        },
                        text = stringResource(R.string.view_statement_lbl)
                    )

                    CardButton( // transactions history
                        onClick = {
                            navController.navigate(route = MobileWalletRoutes.LAST_TRANSACTIONS_SCREEN.name)
                        },
                        text = stringResource(R.string.last_transactions_lbl)
                    )

                }

                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                    ,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ){
                    CardButton( //Profile
                        onClick = {
                            navController.navigate(route = MobileWalletRoutes.PROFILE_SCREEN.name)
                        },
                        text = stringResource(R.string.profile_lbl)
                    )

                    CardButton( //Logout
                        onClick = {
                            showLogOutDialog = true
                        },
                        text = stringResource(R.string.log_out_lbl)
                    )

                }
                VerticalSpacer(10)
                AnimatedVisibility(
                    scrollBehavior.state.collapsedFraction > 0.5
                ) {
                    Column {
                        customer.value?.let {
                            BoldText(
                                text = "Welcome, ${it.firstName}!",
                                fontSize = 30.sp
                            )
                        }
                    }
                }
            }
        }
    }
}