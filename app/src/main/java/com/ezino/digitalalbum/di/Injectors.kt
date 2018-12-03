package com.ezino.digitalalbum.di

import android.app.Application
import com.ezino.digitalalbum.data.AlbumRepository
import com.ezino.digitalalbum.data.AppDatabase
import com.ezino.digitalalbum.viewmodels.AlbumListViewModelFactory

object Injectors {
    private fun getAlbumRepository(application: Application) =
        AlbumRepository.getInstance(AppDatabase.getInstance(application).albumDao())

    public fun provideAlbumListViewModelFactory(application: Application): AlbumListViewModelFactory {
        val respository = getAlbumRepository(application)

        return AlbumListViewModelFactory(respository)
    }
}