package dev.forsythe.mobilewallet.presentation.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dev.forsythe.mobilewallet.R
import dev.forsythe.mobilewallet.presentation.components.CircularProgressIndicatorWallet
import dev.forsythe.mobilewallet.presentation.components.buttons.CardButton
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

    InfoDialog(
        showDialog = homeViewModel.homeScreenState.dialogInfo != null,
        message = homeViewModel.homeScreenState.dialogInfo?:"",
        title = homeViewModel.homeScreenState.dialogTitle?:"",
        onConfirm = {
            homeViewModel.closeDialog()
        }
    )
    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = {
                    Column {
                        BoldText(text = stringResource(R.string.home_screen_tittle))
                        customer.value?.let {
                            Text(text = "Welcome, ${it.firstName}!")
                        }
                    }
                }
            )
        }
    ) {innerPadding->

        Box (
            modifier = Modifier.padding(innerPadding).fillMaxSize(),
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
                           homeViewModel.showDialog(title = "Account Balance", message = "You outstanding balance is KES 50000")
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

                    CardButton( // send money
                        onClick = {
                            navController.navigate(route = MobileWalletRoutes.SEND_MONEY_SCREEN.name)
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
                            homeViewModel.onLogout()
                            //navController.navigate(route = MobileWalletRoutes.LOG_IN_SCREEN.name)
                        },
                        text = stringResource(R.string.log_out_lbl)
                    )

                }
            }
        }
    }
}