package com.example.letssopt.designsystem.component.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.letssopt.data.model.ContentItem

@Composable
fun NewBannerCard(
    item: ContentItem
) {
    Image(
        painter = painterResource(id = item.imageUrl),
        contentDescription = null,
        modifier = Modifier
            .width(280.dp)
            .aspectRatio(7f / 4f)
            .clip(RoundedCornerShape(10.dp)),
        contentScale = ContentScale.Crop
    )
}