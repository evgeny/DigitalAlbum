package com.ezino.digitalalbum

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.ezino.digitalalbum.data.Album
import com.ezino.digitalalbum.di.Injectors
import com.ezino.digitalalbum.viewmodels.AlbumFormViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.album_form_fragment.*
import java.util.*

class AlbumFormFragment : Fragment() {

    private lateinit var viewModel: AlbumFormViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.album_form_fragment, container, false)
        val albumId = arguments?.getInt("albumId") ?: -1
        viewModel =
                ViewModelProviders.of(
                    this,
                    activity?.application?.let { Injectors.provideAlbumFormViewModelFactory(it) })
                    .get(AlbumFormViewModel::class.java)
        viewModel.title().observe(this, Observer { title -> album_title.setText(title) })
        viewModel.getAlbum(albumId)

        return layout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        create_album_btn.setOnClickListener {
            viewModel.addAlbum(
                Album(
                    0,
                    album_title.text.toString(),
                    album_description.text.toString(),
                    Calendar.getInstance()
                )
            )

            Snackbar.make(it, getString(R.string.album_created), Snackbar.LENGTH_LONG).show()
            findNavController().popBackStack()
        }
    }
}