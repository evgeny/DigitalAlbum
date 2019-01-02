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
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.album_card, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val album = getItem(position)

        holder.nameView.text = album.name
        holder.descriptionView.text = album.description
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameView: TextView = itemView.findViewById(R.id.title)
        val descriptionView: TextView = itemView.findViewById(R.id.description)
    }
}