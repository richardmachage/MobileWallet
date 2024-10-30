package dev.forsythe.mobilewallet.presentation.components.buttons

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.forsythe.mobilewallet.R
import dev.forsythe.mobilewallet.presentation.components.texts.BoldText

@Composable
fun CardButton(
    modifier: Modifier = Modifier,
    onClick : () -> Unit,
    text : String
){
    val screenWidth = LocalConfiguration.current.screenWidthDp
    ElevatedCard(
        modifier = modifier
            .width((screenWidth/3).dp)
            .height((screenWidth/3).dp)
            .clickable {
            onClick()
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize().padding(10.dp),
            contentAlignment = Alignment.Center
        ){
            BoldText(text = text, textAlign = TextAlign.Center)
        }
    }
}