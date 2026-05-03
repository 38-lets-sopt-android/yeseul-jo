package com.example.letssopt.presentation.library

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.letssopt.core.data.dao.ContentDao
import com.example.letssopt.core.data.dao.LibraryDao
import com.example.letssopt.core.data.entity.Library
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class LibraryViewModel(
    private val contentDao: ContentDao,
    private val libraryDao: LibraryDao
) : ViewModel() {

    val libraryContents: StateFlow<List<Library>> = libraryDao.getAllLibraryContents()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // 삭제
    fun removeFromLibrary(contentId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            libraryDao.deleteFromLibrary(contentId)
        }
    }

    // Factory
    companion object {
        fun provideFactory(contentDao: ContentDao, libraryDao: LibraryDao): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(LibraryViewModel::class.java)) {
                        @Suppress("UNCHECKED_CAST")
                        return LibraryViewModel(contentDao, libraryDao) as T
                    }
                    throw IllegalArgumentException("Unknown ViewModel class")
                }
            }
    }

}