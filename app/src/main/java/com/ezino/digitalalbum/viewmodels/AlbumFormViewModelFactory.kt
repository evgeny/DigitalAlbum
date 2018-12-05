package com.ezino.digitalalbum.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ezino.digitalalbum.data.AlbumRepository

class AlbumFormViewModelFactory(private val repository: AlbumRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = AlbumFormViewModel(repository) as T
}