package com.ezino.digitalalbum.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ezino.digitalalbum.R
import com.ezino.digitalalbum.data.Album

class AlbumAdapter : ListAdapter<Album, AlbumAdapter.ViewHolder>(AlbumDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.album_card, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val album = getItem(position)

        holder.nameView.text = album.name
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameView: TextView = itemView.findViewById(R.id.title)
    }
}