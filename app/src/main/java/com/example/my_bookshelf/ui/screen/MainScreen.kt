package com.example.my_bookshelf.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.my_bookshelf.R
import com.example.my_bookshelf.data.remote.dto.Book
import com.example.my_bookshelf.data.remote.dto.ImageLinks
import com.example.my_bookshelf.data.remote.dto.VolumeInfo
import com.example.my_bookshelf.ui.theme.MyBookshelfTheme

@Composable
fun MainScreen(
    mainUiState: MainUiState,
    modifier: Modifier
) {
    when (mainUiState) {
        is MainUiState.Loading -> {}
        is MainUiState.Error -> {}
        is MainUiState.Success -> {
            MainDetailScreen(
                books = mainUiState.bookResponse.books,
                modifier = modifier
            )
        }
    }
}

@Composable
private fun MainDetailScreen(
    modifier: Modifier = Modifier,
    books: List<Book>
) {
    LazyColumn(modifier = modifier) {
        items(books) { book ->
            BookContent(
                book = book,
                modifier = Modifier.padding(8.dp).fillMaxWidth()
            )
        }
    }
}

@Composable
private fun BookContent(
    modifier: Modifier = Modifier,
    book: Book
) {
    Card(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                    vertical = 4.dp
                )
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(book.volumeInfo.imageLinks.thumbnail)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.ic_broken_image),
                contentDescription = book.volumeInfo.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(width = 128.dp, height = 172.dp)
            )
            BookVolumeInfoSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                title = book.volumeInfo.title,
                authors = book.volumeInfo.authors,
                pageCount = book.volumeInfo.pageCount,
                publisherDate = book.volumeInfo.publishedDate
            )
        }
    }
}

@Composable
private fun BookVolumeInfoSection(
    modifier: Modifier = Modifier,
    title: String,
    authors: List<String>,
    pageCount: Int,
    publisherDate: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = authors.toString(),
            style = MaterialTheme.typography.titleSmall
        )
        Spacer(modifier = Modifier.height(50.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$pageCount pages",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "Published ${publisherDate.split("-").first()}",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview
@Composable
private fun BookContentPreview() {
    val volumeInfo = VolumeInfo(
        authors = listOf("Ted Gioia"),
        categories = listOf("Social Science"),
        description = "",
        imageLinks = ImageLinks(
            smallThumbnail = "",
            "https://books.google.com/books/content?id=C1MI_4nZyD4C&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"
        ),
        pageCount = 481,
        publishedDate = "1997-11-20",
        publisher = "Oxford University Press, USA",
        title = "The History of Jazz"
    )
    val mockData = Book(id = "C1MI_4nZyD4C", volumeInfo = volumeInfo)

    MyBookshelfTheme {
        BookContent(book = mockData)
    }
}

@Preview
@Composable
private fun MainDetailsScreenPreview() {
    val volumeInfo = VolumeInfo(
        authors = listOf("Ted Gioia"),
        categories = listOf("Social Science"),
        description = "",
        imageLinks = ImageLinks(
            smallThumbnail = "",
            "https://books.google.com/books/content?id=C1MI_4nZyD4C&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"
        ),
        pageCount = 481,
        publishedDate = "1997-11-20",
        publisher = "Oxford University Press, USA",
        title = "The History of Jazz"
    )
    val mockData = List(10) {
        Book(id = "C1MI_4nZyD4C", volumeInfo = volumeInfo)
    }

    MyBookshelfTheme {
        MainDetailScreen(books = mockData)
    }
}