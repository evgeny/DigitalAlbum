package com.ezino.digitalalbum.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ezino.digitalalbum.data.Album
import com.ezino.digitalalbum.data.AlbumRepository

class AlbumFormViewModel internal constructor(private val albumRepository: AlbumRepository) : ViewModel() {
    // state
    private val titleHolder: MutableLiveData<String> = MutableLiveData()

    public fun tile() = titleHolder
    public fun updateTitle(title: String) {
        titleHolder.value = title
    }

    // behavior
    fun addAlbum(album: Album) = albumRepository.addAlbum(album)

//    fun submitAlbum() {
//
//    }
}