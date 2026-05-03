package com.example.letssopt.presentation.library

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
import com.example.letssopt.presentation.library.component.LibraryContent

@Composable
fun LibraryScreen(
    viewModel: LibraryViewModel = viewModel(
        factory = LibraryViewModel .provideFactory(
            AppDatabase.getDatabase(LocalContext.current).contentDao(),
            AppDatabase.getDatabase(LocalContext.current).libraryDao()

        )
    ),
    modifier: Modifier = Modifier
) {
    // DB 관찰
    val libraryList by viewModel.libraryContents.collectAsStateWithLifecycle()

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = "찜한 목록",
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
            items(libraryList, key = { it.id }) { libraryItem ->
                val content = com.example.letssopt.core.data.entity.Content(
                    id = libraryItem.id,
                    title = libraryItem.title,
                    image = libraryItem.image
                )

                LibraryContent(
                    content = content,
                    onDeleteClick = {
                        viewModel.removeFromLibrary(libraryItem.id)
                    }
                )
            }
        }
    }
}