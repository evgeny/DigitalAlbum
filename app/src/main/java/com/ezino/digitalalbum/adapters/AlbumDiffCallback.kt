package com.ezino.digitalalbum.adapters

import androidx.recyclerview.widget.DiffUtil
import com.ezino.digitalalbum.data.Album

class AlbumDiffCallback : DiffUtil.ItemCallback<Album>() {
    override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
        return oldItem.albumId == newItem.albumId
    }
}