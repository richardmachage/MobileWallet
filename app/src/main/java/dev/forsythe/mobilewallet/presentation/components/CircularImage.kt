package dev.forsythe.mobilewallet.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp

@Composable
fun CircularImage(
    modifier : Modifier = Modifier,
    image : Painter,
    height : Int = 50,
    width:Int = 50,
    contentScale: ContentScale = ContentScale.Fit
){
    Box(modifier = modifier.clip(CircleShape)){
        Image(
            modifier = modifier.height(height.dp).width(width.dp).clip(CircleShape),
            painter = image,
            contentDescription = "profile",
            contentScale = contentScale
        )
    }
}