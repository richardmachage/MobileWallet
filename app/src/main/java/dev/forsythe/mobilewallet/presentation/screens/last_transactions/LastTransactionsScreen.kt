package dev.forsythe.mobilewallet.presentation.screens.last_transactions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imeNestedScroll
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.nestedscroll.nestedScrollModifierNode
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import dev.forsythe.mobilewallet.domain.models.TransactionModel
import dev.forsythe.mobilewallet.presentation.components.CircularProgressIndicatorWallet
import dev.forsythe.mobilewallet.presentation.components.buttons.BackButton
import dev.forsythe.mobilewallet.presentation.components.texts.BoldText
import dev.forsythe.mobilewallet.presentation.screens.last_transactions.components.TransactionItem
import dev.forsythe.mobilewallet.presentation.screens.last_transactions.components.TransactionListItem
import io.ktor.client.utils.EmptyContent.status
import io.ktor.http.headers

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LastTransactionsScreen(
    navController: NavController
){
    val lastTransactionsViewModel = hiltViewModel<LastTransactionsViewModel>()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    //Paged data for optimized reading from database
    val listOfTransactions : LazyPagingItems<TransactionModel> = lastTransactionsViewModel.getAllTransactions().collectAsLazyPagingItems()


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
                        title = { BoldText(text = "Transactions History") },
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

    ) {
        Column (
            modifier = Modifier.fillMaxSize().padding(it),
        ){
            Row (
                modifier = Modifier.align(Alignment.CenterHorizontally)
                    .padding(10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                BoldText(
                    text = "Account",
                    fontSize = 20.sp,
                )

                BoldText(
                    text = "Amount",
                    fontSize = 22.sp,
                    )

                BoldText(
                    text = "Status",
                    fontSize = 22.sp,
                    )
            }
            LazyColumn{
                items(
                    count = listOfTransactions.itemCount,
                    key = listOfTransactions.itemKey{trans -> trans.id},
                    contentType = listOfTransactions.itemContentType{"Transaction Model"}
                ){index->
                    val transaction = listOfTransactions[index]

                    transaction?.let {
                        TransactionListItem(
                            transactionItem = TransactionItem(
                                id = transaction.id,
                                accountTo = transaction.accountTo,
                                amount = transaction.amount.toString(),
                                status = transaction.status
                            )
                        )
                    }
                }
            }
        }
    }

}