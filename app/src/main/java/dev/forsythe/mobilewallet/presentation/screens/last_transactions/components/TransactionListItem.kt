package dev.forsythe.mobilewallet.presentation.screens.last_transactions.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.forsythe.mobilewallet.data.data_source.TransactionStatus

@Composable
fun TransactionListItem(transactionItem: TransactionItem){
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 10.dp)
        ,
        shape = RoundedCornerShape(10)
    ){
        HorizontalDivider()
        Row(
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            //account to
            Text(text = transactionItem.accountTo)
            Text(text = "KSH " + transactionItem.amount)
            Text(
                text = transactionItem.status,
                textAlign = TextAlign.End,
                color = if (transactionItem.status == TransactionStatus.FAILED.name ) Color.Red  else LocalContentColor.current
            )
        }
    }
}