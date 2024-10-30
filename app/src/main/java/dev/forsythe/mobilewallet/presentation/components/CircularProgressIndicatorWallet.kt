package dev.forsythe.mobilewallet.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import dev.forsythe.mobilewallet.presentation.components.texts.BoldText

@Composable
fun CircularProgressIndicatorWallet(
    isLoading : Boolean ,
    displayText : String?
) {
        if (isLoading) {
            Dialog(
                onDismissRequest = {}
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        CircularProgressIndicator(
                            modifier = Modifier.padding(10.dp),
                        )
                        displayText?.let {
                            BoldText(text = it)
                        }
                    }
                }
            }
        }

}