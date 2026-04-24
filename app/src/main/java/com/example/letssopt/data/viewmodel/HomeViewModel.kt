package com.example.letssopt.data.viewmodel

import androidx.lifecycle.ViewModel
import com.example.letssopt.R
import com.example.letssopt.data.model.ContentItem

class HomeViewModel : ViewModel() {
    val newContents = listOf(
        ContentItem(1, null, null, R.drawable.new_1),
        ContentItem(2, null, null, R.drawable.new_2),
        ContentItem(3, null, null, R.drawable.new_3),
    )

    val watGorithmContents = listOf(
        ContentItem(4, null, null, R.drawable.content_1),
        ContentItem(5, null, null, R.drawable.content_2),
        ContentItem(6, null, null, R.drawable.content_3),
        ContentItem(7, null, null, R.drawable.content_1),
        ContentItem(8, null, null, R.drawable.content_2),
        ContentItem(9, null, null, R.drawable.content_3),
    )

    val watchaPartyContents = listOf(
        ContentItem(10, "왕과 사는 남자", "21:30", R.drawable.party_1),
        ContentItem(11, "파묘", "22:22", R.drawable.party_2),
    )
}