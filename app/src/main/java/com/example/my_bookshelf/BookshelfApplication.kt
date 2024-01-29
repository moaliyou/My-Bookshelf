package com.example.my_bookshelf

import android.app.Application
import com.example.my_bookshelf.di.AppContainer
import com.example.my_bookshelf.di.DefaultAppContainer

class BookshelfApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}