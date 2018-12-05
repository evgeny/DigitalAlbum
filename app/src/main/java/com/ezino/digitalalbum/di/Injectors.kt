package com.ezino.digitalalbum.di

import android.app.Application
import com.ezino.digitalalbum.data.AlbumRepository
import com.ezino.digitalalbum.data.AppDatabase
import com.ezino.digitalalbum.viewmodels.AlbumFormViewModelFactory
import com.ezino.digitalalbum.viewmodels.AlbumListViewModelFactory

object Injectors {
    private fun getAlbumRepository(application: Application) =
        AlbumRepository.getInstance(AppDatabase.getInstance(application).albumDao())

    fun provideAlbumListViewModelFactory(application: Application): AlbumListViewModelFactory {
        val repository = getAlbumRepository(application)

        return AlbumListViewModelFactory(repository)
    }

    fun provideAlbumFormViewModelFactory(application: Application): AlbumFormViewModelFactory {
        val repository = getAlbumRepository(application)

        return AlbumFormViewModelFactory(repository)
    }
}