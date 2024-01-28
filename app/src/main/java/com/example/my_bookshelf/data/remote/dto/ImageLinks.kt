package com.example.my_bookshelf.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ImageLinks(
    val smallThumbnail: String,
    val thumbnail: String
)