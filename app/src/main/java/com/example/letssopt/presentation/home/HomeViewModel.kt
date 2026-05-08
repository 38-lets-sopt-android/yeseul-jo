package com.example.letssopt.presentation.home

import androidx.lifecycle.ViewModel
import com.example.letssopt.R
import com.example.letssopt.presentation.home.model.ContentItem
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class HomeState(
    val newContents: ImmutableList<ContentItem> = persistentListOf(),
    val watGorithmContents: ImmutableList<ContentItem> = persistentListOf(),
    val watchaPartyContents: ImmutableList<ContentItem> = persistentListOf()
)

class HomeViewModel : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        loadHomeContents()
    }

    private fun loadHomeContents() {
        _state.value = _state.value.copy(
            newContents = persistentListOf(
                ContentItem(1, null, null, R.drawable.new_1),
                ContentItem(2, null, null, R.drawable.new_2),
                ContentItem(3, null, null, R.drawable.new_3),
            ),
            watGorithmContents = persistentListOf(
                ContentItem(4, null, null, R.drawable.content_1),
                ContentItem(5, null, null, R.drawable.content_2),
                ContentItem(6, null, null, R.drawable.content_3),
                ContentItem(7, null, null, R.drawable.content_1),
                ContentItem(8, null, null, R.drawable.content_2),
                ContentItem(9, null, null, R.drawable.content_3),
            ),
            watchaPartyContents = persistentListOf(
                ContentItem(10, "왕과 사는 남자", "21:30", R.drawable.party_1),
                ContentItem(11, "파묘", "22:22", R.drawable.party_2),
            )
        )
    }
}