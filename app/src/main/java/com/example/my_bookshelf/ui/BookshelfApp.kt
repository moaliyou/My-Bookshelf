package com.example.my_bookshelf.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.my_bookshelf.ui.screen.MainScreen
import com.example.my_bookshelf.ui.screen.MainViewModel

@Composable
fun BookshelfApp() {
    val mainViewModel: MainViewModel = viewModel(factory = MainViewModel.factory)
    val mainUiState = mainViewModel.uiState.collectAsState().value

    MainScreen(
        mainUiState = mainUiState,
        modifier = Modifier.fillMaxSize().padding(8.dp)
    )
}