package com.example.my_bookshelf.data.remote.network

object Routes {
    private const val BASE_URL = "https://www.googleapis.com/books"
    private const val VERSION = "v1"

    const val VOLUMES = "$BASE_URL/$VERSION/volumes"
}