package com.example.my_bookshelf.data.remote.repository

import com.example.my_bookshelf.data.remote.dto.Book
import com.example.my_bookshelf.data.remote.dto.BookResponse
import com.example.my_bookshelf.data.remote.network.Routes
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url

class NetworkBookRepository(
    private val client: HttpClient
) : BookRepository {
    override suspend fun getBooks(query: String): BookResponse? {
        return try {
            client.get {
                url(Routes.VOLUMES)
                parameter("q", query)
            }.body()
        } catch (e: ServerResponseException) {
            println(e.message)
            null
        } catch (e: ClientRequestException) {
            println(e.message)
            null
        }
    }

    override suspend fun getBook(id: String): Book? {
        return try {
            client.get {
                url(Routes.VOLUMES)
                parameter("/", id)
            }.body()
        } catch (e: ServerResponseException) {
            println(e.message)
            null
        } catch (e: ClientRequestException) {
            println(e.message)
            null
        }
    }
}