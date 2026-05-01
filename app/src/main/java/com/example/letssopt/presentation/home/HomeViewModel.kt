package com.example.letssopt.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.letssopt.R
import com.example.letssopt.presentation.home.model.ContentItem

data class HomeState(
    val newContents: List<ContentItem> = emptyList(),
    val watGorithmContents: List<ContentItem> = emptyList(),
    val watchaPartyContents: List<ContentItem> = emptyList()
)

class HomeViewModel : ViewModel() {
    var state by mutableStateOf(HomeState())
        private set

    init {
        loadHomeContents()
    }

    private fun loadHomeContents() {
        state = HomeState(
            newContents = listOf(
                ContentItem(1, null, null, R.drawable.new_1),
                ContentItem(2, null, null, R.drawable.new_2),
                ContentItem(3, null, null, R.drawable.new_3),
            ),
            watGorithmContents = listOf(
                ContentItem(4, null, null, R.drawable.content_1),
                ContentItem(5, null, null, R.drawable.content_2),
                ContentItem(6, null, null, R.drawable.content_3),
                ContentItem(7, null, null, R.drawable.content_1),
                ContentItem(8, null, null, R.drawable.content_2),
                ContentItem(9, null, null, R.drawable.content_3),
            ),
            watchaPartyContents = listOf(
                ContentItem(10, "왕과 사는 남자", "21:30", R.drawable.party_1),
                ContentItem(11, "파묘", "22:22", R.drawable.party_2),
            )
        )
    }
}