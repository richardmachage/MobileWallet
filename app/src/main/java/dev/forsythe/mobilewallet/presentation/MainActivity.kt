package dev.forsythe.mobilewallet.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.forsythe.mobilewallet.presentation.navigation.MobileWalletRoutes
import dev.forsythe.mobilewallet.presentation.screens.home.HomeScreen
import dev.forsythe.mobilewallet.presentation.screens.last_transactions.LastTransactionsScreen
import dev.forsythe.mobilewallet.presentation.screens.log_in.LogInScreen
import dev.forsythe.mobilewallet.presentation.screens.profile.ProfileScreen
import dev.forsythe.mobilewallet.presentation.screens.send_money.SendMoneyScreen
import dev.forsythe.mobilewallet.presentation.screens.statements.StatementsScreen
import dev.forsythe.mobilewallet.presentation.theme.MobileWalletTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainActivityViewModel by viewModels<MainViewModel>()

        installSplashScreen()
        enableEdgeToEdge()
        setContent {

            MobileWalletTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = mainActivityViewModel.setStartDestination()
                ){
                    composable(route = MobileWalletRoutes.HOME_SCREEN.name){
                        HomeScreen(navController)
                    }

                    composable(route = MobileWalletRoutes.LOG_IN_SCREEN.name){
                        LogInScreen(navController)
                    }

                    composable(route = MobileWalletRoutes.PROFILE_SCREEN.name){
                        ProfileScreen(navController)
                    }

                    composable(route = MobileWalletRoutes.SEND_MONEY_SCREEN.name){
                        SendMoneyScreen(navController)
                    }

                    composable(route = MobileWalletRoutes.LAST_TRANSACTIONS_SCREEN.name){
                        LastTransactionsScreen(navController)
                    }

                    composable(route = MobileWalletRoutes.STATEMENTS_SCREEN.name){
                        StatementsScreen(navController)
                    }
                }
            }
        }
    }
}
