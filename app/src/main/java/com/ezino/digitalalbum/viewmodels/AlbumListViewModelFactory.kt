package com.ezino.digitalalbum.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ezino.digitalalbum.data.AlbumRepository

class AlbumListViewModelFactory(private val repository: AlbumRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = AlbumListViewModel(repository) as T
}