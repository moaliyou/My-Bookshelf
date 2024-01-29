package com.example.my_bookshelf.di

import com.example.my_bookshelf.data.remote.network.KtorInstance.client
import com.example.my_bookshelf.data.remote.repository.BookRepository
import com.example.my_bookshelf.data.remote.repository.NetworkBookRepository

class DefaultAppContainer : AppContainer {
    override val bookRepository: BookRepository by lazy {
        NetworkBookRepository(client)
    }
}