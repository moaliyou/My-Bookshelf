package com.example.my_bookshelf.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class VolumeInfo(
    val authors: List<String>,
    val categories: List<String>,
    val description: String? = null,
    val imageLinks: ImageLinks,
    val pageCount: Int,
    val publishedDate: String,
    val publisher: String? = null,
    val title: String
)