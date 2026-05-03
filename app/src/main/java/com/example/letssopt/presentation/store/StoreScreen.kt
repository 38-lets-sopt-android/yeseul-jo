package com.example.letssopt.presentation.store

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.letssopt.core.data.AppDatabase
import com.example.letssopt.presentation.store.component.StoreContent

@Composable
fun StoreScreen(
    viewModel: StoreViewModel = viewModel(
        factory = StoreViewModel.provideFactory(
            AppDatabase.getDatabase(LocalContext.current).contentDao()
        )
    ),
    modifier: Modifier = Modifier
) {
    // DB 관찰
    val contents by viewModel.storeContents.collectAsStateWithLifecycle()
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = "개별 구매",
            style = typography.headlineMedium,
            modifier = Modifier.padding(
                top = 70.dp,
                bottom = 45.dp,
                start = 16.dp
            )
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(contents) { item ->
                StoreContent(item)
            }
        }
    }

}

