package com.example.my_bookshelf.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.my_bookshelf.BookshelfApplication
import com.example.my_bookshelf.data.remote.repository.BookRepository
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val bookRepository: BookRepository
) : ViewModel() {
    private val query = "jazz+history"
    private val _uiState = MutableStateFlow<MainUiState>(MainUiState.Loading)
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    init {
        getBooks(query)
    }

    fun getBooks(query: String) {
        viewModelScope.launch {
            _uiState.value = MainUiState.Loading
            _uiState.value = try {
                MainUiState.Success(bookRepository.getBooks(query)!!)
            } catch (e: ServerResponseException) {
                MainUiState.Error
            } catch (e: ClientRequestException) {
                MainUiState.Error
            }
        }
    }

    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BookshelfApplication)
                val bookRepository = application.container.bookRepository
                MainViewModel(bookRepository)
            }
        }
    }
}