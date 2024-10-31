package dev.forsythe.mobilewallet.presentation.screens.statements

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dev.forsythe.mobilewallet.presentation.components.buttons.BackButton
import dev.forsythe.mobilewallet.presentation.components.buttons.OutlineButtonWallet
import dev.forsythe.mobilewallet.presentation.components.texts.BoldText
import dev.forsythe.mobilewallet.presentation.screens.statements.components.StatementItem
import dev.forsythe.mobilewallet.presentation.screens.statements.components.StatementListItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatementsScreen(
    navController: NavController
){
    val statementViewModel = hiltViewModel<StatementViewModel>()
    val context  = LocalContext.current
    val records by statementViewModel.records.collectAsState() //collects all the records
    var displayedRecords by remember { mutableIntStateOf(10) }
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    LaunchedEffect(Unit) {
        statementViewModel.getAllRecords()
    }

    LaunchedEffect(statementViewModel.statementScreenState.toastMessage) {
        val message = statementViewModel.statementScreenState.toastMessage
        message?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            statementViewModel.resetToast()
        }
    }
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                title = { BoldText(text = "Statement") },
                navigationIcon = {
                    BackButton(onClick = {navController.navigateUp()})
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) { paddingValues ->
        Column (
            modifier = Modifier.padding(paddingValues).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            Row (
                modifier = Modifier.align(Alignment.CenterHorizontally)
                    .padding(end = 25.dp, start=25.dp, top = 20.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                BoldText(
                    text = "Transaction ID",
                    fontSize = 20.sp,
                )

                BoldText(
                    text = "Amount",
                    fontSize = 22.sp,
                )
            }

            LazyColumn()
            {
                //local pagination
                items(records.take(displayedRecords)) { record ->
                    StatementListItem(
                        StatementItem(
                            transactionId = record.transactionId,
                            amount = record.amount.toString()
                        )
                    )
                }
                item {
                    if (displayedRecords < records.size) {
                        //Load more button
                        OutlineButtonWallet(
                           modifier =  Modifier.align(Alignment.CenterHorizontally).fillMaxWidth().padding(10.dp),
                            onClick = { displayedRecords += 20 },
                            text = "Load More"
                        )

                    }
                }
            }

            Row (
                modifier = Modifier.align(Alignment.CenterHorizontally)
                    .padding(end = 25.dp, start=25.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                BoldText(
                    text = "Total",
                    fontSize = 22.sp,
                )

                BoldText(
                    text = "KSH " + records.sumOf { it.amount }.toString(),
                    fontSize = 22.sp,
                )
            }
        }
    }

}