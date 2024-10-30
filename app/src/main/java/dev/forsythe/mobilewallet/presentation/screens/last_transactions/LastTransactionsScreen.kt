package dev.forsythe.mobilewallet.presentation.screens.last_transactions

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import dev.forsythe.mobilewallet.presentation.components.buttons.BackButton
import dev.forsythe.mobilewallet.presentation.components.texts.BoldText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LastTransactionsScreen(
    navController: NavController
){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {BoldText(text = "Last Transactions")},
                navigationIcon = {
                    BackButton(onClick = {navController.navigateUp()})
                }
            )
        }
    ) {
        Box (
            modifier = Modifier.fillMaxSize().padding(it),
            contentAlignment = Alignment.Center
        ){
            Text(text = "Last Transactions")
        }
    }

}