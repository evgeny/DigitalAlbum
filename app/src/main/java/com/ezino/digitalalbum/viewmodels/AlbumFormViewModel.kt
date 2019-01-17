package com.ezino.digitalalbum.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ezino.digitalalbum.data.Album
import com.ezino.digitalalbum.data.AlbumRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class AlbumFormViewModel internal constructor(private val albumRepository: AlbumRepository) : ViewModel(),
    CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + SupervisorJob()

    // state
    private val titleHolder: MutableLiveData<String> = MutableLiveData()

    public fun title() = titleHolder
    public fun updateTitle(title: String) {
        titleHolder.value = title
    }

    // behavior
    fun addAlbum(album: Album) = launch {
        withContext(Dispatchers.IO) {
            albumRepository.addAlbum(album)
        }

        // TODO navigate back
        // TODO show notification
    }

    fun getAlbum(id: Int) = launch {
        withContext(Dispatchers.IO) {
            val album = albumRepository.findAlbum(id)
        }

        // update ui
//        titleHolder.value = al
    }


    override fun onCleared() {
        super.onCleared()

        coroutineContext.cancelChildren()
    }
}