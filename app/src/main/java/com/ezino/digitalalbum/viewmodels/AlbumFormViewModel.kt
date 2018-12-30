package com.ezino.digitalalbum.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ezino.digitalalbum.data.Album
import com.ezino.digitalalbum.data.AlbumRepository
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*

class AlbumFormViewModel internal constructor(private val albumRepository: AlbumRepository) : ViewModel() {
    // coroutine
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    // state
    private val titleHolder: MutableLiveData<String> = MutableLiveData()
    public fun title() = titleHolder

    // behaviour

    public fun updateTitle(title: String) {
        titleHolder.value = title
    }

    // behavior
    fun addAlbum(album: Album) {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                albumRepository.addAlbum(album)
            }

            // TODO navigate back
        }
    }

    override fun onCleared() {
        super.onCleared()

        viewModelJob.cancel()
    }
}