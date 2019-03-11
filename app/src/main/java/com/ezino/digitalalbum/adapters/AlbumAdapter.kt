package com.ezino.digitalalbum.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ezino.digitalalbum.R
import com.ezino.digitalalbum.data.Album

class AlbumAdapter(private val clickListener: AlbumCardClickListener) :
    ListAdapter<Album, AlbumAdapter.ViewHolder>(AlbumDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.album_card, parent, false)
//        itemView.setOnClickListener(clickListener)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val album = getItem(position)

        holder.itemView.tag = album.albumId
        holder.nameView.text = album.name
        holder.descriptionView.text = album.description

        holder.deleteButton.setOnClickListener { v -> clickListener.onClickDelete(v, position) }
        holder.itemView.setOnClickListener { v -> clickListener.onClickEdit(v, position) }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameView: TextView = itemView.findViewById(R.id.title)
        val descriptionView: TextView = itemView.findViewById(R.id.description)

        var deleteButton: Button = itemView.findViewById(R.id.btn_delete_album)
    }

    interface AlbumCardClickListener {
        fun onClickDelete(v: View, position: Int)
        fun onClickEdit(v: View, position: Int)
    }
}