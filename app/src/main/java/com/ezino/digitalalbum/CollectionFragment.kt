package com.ezino.digitalalbum


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ezino.digitalalbum.adapters.AlbumAdapter
import com.ezino.digitalalbum.di.Injectors
import com.ezino.digitalalbum.viewmodels.AlbumListViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

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

        val adapter = AlbumAdapter(View.OnClickListener { v -> createClickListener(v.tag as Int) })
        viewModel.getAlbums().observe(this, Observer { albums -> if (albums != null) adapter.submitList(albums) })
        val recyclerView = view.findViewById(R.id.album_list) as RecyclerView
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this.context)
            this.adapter = adapter
        }

        val addAlbumButton = view.findViewById<FloatingActionButton>(R.id.fab)
        addAlbumButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_collectionFragment_to_albumFormFragment))
    }

    private fun createClickListener(albumId: Int = -1) = NavHostFragment.findNavController(this)
        .navigate(R.id.albumFormFragment, Bundle(1).apply { putInt("albumId", albumId) })
}
