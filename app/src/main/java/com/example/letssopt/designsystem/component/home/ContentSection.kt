package com.example.letssopt.designsystem.component.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.letssopt.data.model.ContentItem

@Composable
fun ContentSection(
    items: List<ContentItem>,
    title: Int? = null,
    titleIcon: Int? = null,
    subtitle: Int? = null,
    showMore: Boolean = false,
    spacerHeight: Int = 8,
    modifier: Modifier = Modifier,
    content: @Composable (ContentItem) -> Unit
) {
    Column(modifier = modifier) {
        SectionHeader(
            title = title,
            titleIcon = titleIcon,
            subtitle = subtitle,
            showMore = showMore
        )

        Spacer(modifier = Modifier.height(spacerHeight.dp))

        LazyRow(
            contentPadding = PaddingValues(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(items, key = { it.id }) { item ->
                content(item)
            }
        }
    }
}