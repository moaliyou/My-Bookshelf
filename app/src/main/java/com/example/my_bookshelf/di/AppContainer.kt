package com.example.my_bookshelf.di

import com.example.my_bookshelf.data.remote.repository.BookRepository

interface AppContainer {
    val bookRepository: BookRepository
}