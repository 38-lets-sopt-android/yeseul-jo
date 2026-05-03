package com.example.letssopt.presentation.store

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.letssopt.core.data.AppDatabase
import com.example.letssopt.presentation.store.component.StoreContent

@Composable
fun StoreScreen(
    viewModel: StoreViewModel = viewModel(
        factory = StoreViewModel.provideFactory(AppDatabase.getDatabase(LocalContext.current).contentDao())
    ),
    modifier: Modifier = Modifier
) {
    // DB 관찰
    val contents by viewModel.storeContents.collectAsStateWithLifecycle()

    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(3)
    ) {
        items(contents) { item ->
            StoreContent(item)
        }
    }

}

