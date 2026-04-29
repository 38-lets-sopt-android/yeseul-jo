package com.example.letssopt.designsystem.component.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.letssopt.R
import com.example.letssopt.designsystem.theme.TextSecondary

@Composable
fun SectionHeader(
    modifier: Modifier = Modifier,
    title: Int? = null,
    titleIcon: Int? = null,
    subtitle: Int? = null,
    showMore: Boolean = false
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 19.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            if (titleIcon != null) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_watgorithm),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            } else if (title != null) {
                Text(
                    text = stringResource(id = title),
                    style = typography.headlineMedium,
                    color = Color.White
                )
            }

            subtitle?.let {
                Text(
                    text = stringResource(id = it),
                    color = TextSecondary,
                    style = typography.headlineMedium
                )
            }
        }

        if (showMore) {
            Text(
                text = stringResource(id = R.string.home_show_more),
                style = typography.bodySmall,
                color = TextSecondary
            )
        }
    }
}