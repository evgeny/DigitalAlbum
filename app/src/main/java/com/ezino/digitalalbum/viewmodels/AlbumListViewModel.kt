package com.ezino.digitalalbum.viewmodels

import androidx.lifecycle.ViewModel
import com.ezino.digitalalbum.data.Album
import com.ezino.digitalalbum.data.AlbumRepository
import kotlinx.coroutines.*
import java.util.*
import kotlin.coroutines.CoroutineContext

class AlbumListViewModel internal constructor(private val albumRepository: AlbumRepository) : ViewModel(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + SupervisorJob()

    private val albumList = albumRepository.getAlbums()

    fun getAlbums() = albumList

    fun add(album: Album) = albumRepository.addAlbum(album)

    fun delete(albumId: Int) = launch {
        withContext(Dispatchers.IO) {
            val album = Album(albumId, "", "", Calendar.getInstance())
            albumRepository.deleteAlbum(album)
        }
    }

    override fun onCleared() {
        super.onCleared()

        coroutineContext.cancelChildren()
    }
}
