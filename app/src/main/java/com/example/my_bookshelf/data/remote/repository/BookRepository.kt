package com.example.my_bookshelf.data.remote.repository

import com.example.my_bookshelf.data.remote.dto.Book
import com.example.my_bookshelf.data.remote.dto.BookResponse

interface BookRepository {
    suspend fun getBooks(query: String): BookResponse?
    suspend fun getBook(id: String): Book?
}