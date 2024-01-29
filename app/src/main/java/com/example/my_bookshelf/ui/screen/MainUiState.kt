package com.example.my_bookshelf.ui.screen

import com.example.my_bookshelf.data.remote.dto.BookResponse

sealed interface MainUiState {
    data object Loading : MainUiState
    data object Error : MainUiState
    data class Success(val bookResponse: BookResponse) : MainUiState
}