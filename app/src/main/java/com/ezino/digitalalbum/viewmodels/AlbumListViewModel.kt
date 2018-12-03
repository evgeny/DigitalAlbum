package com.ezino.digitalalbum.viewmodels

import androidx.lifecycle.ViewModel
import com.ezino.digitalalbum.data.Album
import com.ezino.digitalalbum.data.AlbumRepository

class AlbumListViewModel(private val albumRepository: AlbumRepository) : ViewModel() {
    private val albumList = albumRepository.getAlbums()

    public fun getAlbums() = albumList

    public fun add(album: Album) = albumRepository.addAlbum(album)
}