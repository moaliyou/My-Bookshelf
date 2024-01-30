package com.example.my_bookshelf.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookResponse(
    @SerialName("items") val books: List<Book>,
    val kind: String,
    @SerialName("totalItems") val totalBooks: Int
)