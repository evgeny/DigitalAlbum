package com.ezino.digitalalbum.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ezino.digitalalbum.data.Album
import com.ezino.digitalalbum.data.AlbumRepository
import kotlinx.coroutines.*

class AlbumFormViewModel internal constructor(private val albumRepository: AlbumRepository) : ViewModel() {
    private val viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    // state
    private val titleHolder: MutableLiveData<String> = MutableLiveData()

    public fun tile() = titleHolder
    public fun updateTitle(title: String) {
        titleHolder.value = title
    }

    // behavior
    fun addAlbum(album: Album) {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                albumRepository.addAlbum(album)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()

        viewModelJob.cancel()
    }
}