package com.example.my_bookshelf.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class Book(
    val id: String,
    val volumeInfo: VolumeInfo
)