package com.example.letssopt.presentation.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.letssopt.R
import com.example.letssopt.core.designsystem.theme.PrimaryRed
import com.example.letssopt.core.designsystem.theme.Surface
import com.example.letssopt.core.designsystem.theme.TextPrimary
import com.example.letssopt.presentation.home.model.ContentItem

@Composable
fun WatchaPartyCard(
    item: ContentItem,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .width(196.dp)
            .background(Surface)
    ) {
        Box {
            Image(
                painter = painterResource(id = item.imageUrl),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp),
                contentScale = ContentScale.Crop
            )

            Surface(
                color = Color.White,
                shape = CircleShape,
                modifier = Modifier
                    .padding(top = 7.dp, end = 5.dp)
                    .size(35.dp)
                    .align(Alignment.TopEnd)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_watcha_party_noti),
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }

        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = stringResource(id = R.string.home_watcha_party_time_format, item.startTime ?: ""),
                color = PrimaryRed,
                style = typography.bodySmall
            )
            Text(
                text = stringResource(id = R.string.home_watcha_party_title_format, item.title ?: ""),
                color = TextPrimary,
                style = typography.bodySmall,
            )
        }
    }
}