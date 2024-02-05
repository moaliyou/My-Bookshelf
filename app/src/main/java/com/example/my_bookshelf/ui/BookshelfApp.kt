package com.example.my_bookshelf.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.my_bookshelf.ui.screen.BookScreen
import com.example.my_bookshelf.ui.screen.BookViewModel
import com.example.my_bookshelf.ui.screen.MainScreen
import com.example.my_bookshelf.ui.screen.MainViewModel

sealed class Screen(val route: String) {
    data object Main : Screen("main")
    data object Book : Screen("book")
}

@Composable
fun BookshelfApp() {
    val mainViewModel: MainViewModel = viewModel(factory = MainViewModel.factory)
    val mainUiState = mainViewModel.uiState.collectAsState().value

    val bookViewModel: BookViewModel = viewModel(factory = BookViewModel.factory)
    val bookUiState = bookViewModel.uiState.collectAsState().value

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Main.route) {
        composable(route = Screen.Main.route) {
            MainScreen(
                mainUiState = mainUiState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                onClick = {
                    navController.navigate(route = Screen.Book.route)
                    bookViewModel.getBook(it)
                }
            )
        }
        composable(route = Screen.Book.route) {
            BookScreen(
                bookUiState = bookUiState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            )
        }
    }

}