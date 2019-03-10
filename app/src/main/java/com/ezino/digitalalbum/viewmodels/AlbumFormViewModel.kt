package com.ezino.digitalalbum.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.ezino.digitalalbum.data.Album
import com.ezino.digitalalbum.data.AlbumRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class AlbumFormViewModel internal constructor(private val albumRepository: AlbumRepository) : ViewModel(),
    CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + SupervisorJob()


    fun getAlbumName(albumId: Int): LiveData<String> {
        return Transformations.map(albumRepository.findAlbum(albumId)) { a -> a?.name ?: "" }
    }

    fun getAlbumDescription(albumId: Int): LiveData<String> {
        return Transformations.map(albumRepository.findAlbum(albumId)) { a -> a?.description ?: "" }
    }

    // behavior
    fun addAlbum(album: Album) = launch {
        withContext(Dispatchers.IO) {
            albumRepository.addAlbum(album)
        }

        // TODO navigate back
        // TODO show notification
    }

    override fun onCleared() {
        super.onCleared()

        coroutineContext.cancelChildren()
    }
}