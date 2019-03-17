package com.ezino.digitalalbum.data

class AlbumRepository private constructor(private val albumDao: AlbumDao) {
    companion object {
        @Volatile
        private var instance: AlbumRepository? = null

        fun getInstance(albumDao: AlbumDao) = instance ?: synchronized(this) {
            instance ?: AlbumRepository(albumDao).also { instance = it }
        }
    }

    fun addAlbum(album: Album) = albumDao.insertNew(album)

    fun updateAlbum(album: Album) = albumDao.updateAlbum(album)

    fun getAlbums() = albumDao.getAlbums()

    fun findAlbum(albumId: Int) = albumDao.getAlbum(albumId)

    fun deleteAlbum(album: Album) = albumDao.deleteAlbum(album)
}