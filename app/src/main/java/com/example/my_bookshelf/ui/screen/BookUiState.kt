package com.example.my_bookshelf.ui.screen

import com.example.my_bookshelf.data.remote.dto.Book

sealed interface BookUiState {
    data object Loading : BookUiState
    data object Error : BookUiState
    data class Success(val book: Book) : BookUiState
}