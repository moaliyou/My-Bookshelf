package com.example.my_bookshelf.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
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
fun BookScreen(
    modifier: Modifier = Modifier,
    bookUiState: BookUiState
) {
    when (bookUiState) {
        is BookUiState.Loading -> {}
        is BookUiState.Error -> {}
        is BookUiState.Success -> {
            BookDetails(book = bookUiState.book, modifier = modifier)
        }
    }
}

@Composable
fun BookDetails(
    modifier: Modifier = Modifier,
    book: Book
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = book.volumeInfo.title, style = MaterialTheme.typography.titleLarge)
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(
                    book.volumeInfo.imageLinks.thumbnail.replace(
                        "http",
                        "https"
                    )
                )
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.ic_broken_image),
            contentDescription = book.volumeInfo.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(width = 104.dp, height = 141.dp)
                .clip(RoundedCornerShape(2.dp))
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column {
                Text(
                    text = "Authors",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = book.volumeInfo.authors.joinToString("\n"),
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Column {
                Text(
                    text = "Categories",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = book.volumeInfo.categories.joinToString("\n"),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(text = "Description", style = MaterialTheme.typography.titleMedium)
            Text(
                text = book.volumeInfo.description!!,
                textAlign = TextAlign.Justify,
                style = MaterialTheme.typography.bodySmall
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun BookDetailsPreview() {
    val volumeInfo = VolumeInfo(
        authors = listOf("Ted Gioia", "Iman Khan", "John Smith"),
        categories = listOf("Social Science"),
        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
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
        BookDetails(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            book = mockData
        )
    }
}