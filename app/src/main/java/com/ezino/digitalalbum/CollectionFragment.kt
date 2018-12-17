package com.ezino.digitalalbum


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ezino.digitalalbum.adapters.AlbumAdapter
import com.ezino.digitalalbum.di.Injectors
import com.ezino.digitalalbum.viewmodels.AlbumListViewModel
import kotlinx.android.synthetic.main.content_album_collection.*

/**
 * A simple [Fragment] subclass.
 *
 */
class CollectionFragment : Fragment() {

    private lateinit var viewModel: AlbumListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val factory = activity?.application?.let { Injectors.provideAlbumListViewModelFactory(it) }
        viewModel = ViewModelProviders.of(this, factory).get(AlbumListViewModel::class.java)

        return inflater.inflate(R.layout.content_album_collection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = AlbumAdapter()
        album_list.adapter = adapter
        viewModel.getAlbums().observe(this, Observer { albums -> if (albums != null) adapter.submitList(albums) })
    }
}
