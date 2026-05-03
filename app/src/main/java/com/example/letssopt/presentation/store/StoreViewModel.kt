package com.example.letssopt.presentation.store

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.letssopt.R
import com.example.letssopt.core.data.dao.ContentDao
import com.example.letssopt.core.data.entity.Content
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class StoreViewModel(private val contentDao: ContentDao) : ViewModel() {

    init {
        insertData()
    }

    private fun insertData() {
        viewModelScope.launch(Dispatchers.IO) {
            val dummyList = listOf(
                Content(title = "이 사랑 통역 되나요", image = R.drawable.content_1),
                Content(title = "이상한일5", image = R.drawable.content_2),
                Content(title = "하일매리", image = R.drawable.content_3),
                Content(title = "이 사랑 통역 되나요", image = R.drawable.content_1),
                Content(title = "이상한일5", image = R.drawable.content_2),
            )

            dummyList.forEach {
                contentDao.insert(it)
            }
        }
    }

    // 데이터 관찰
    val storeContents: StateFlow<List<Content>> = contentDao.getAllContents()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // Factory
    companion object {
        fun provideFactory(contentDao: ContentDao): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(StoreViewModel::class.java)) {
                        @Suppress("UNCHECKED_CAST")
                        return StoreViewModel(contentDao) as T
                    }
                    throw IllegalArgumentException("Unknown ViewModel class")
                }
            }
    }
}