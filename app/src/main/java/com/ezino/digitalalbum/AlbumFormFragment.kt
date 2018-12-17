package com.ezino.digitalalbum

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ezino.digitalalbum.data.Album
import com.ezino.digitalalbum.di.Injectors
import com.ezino.digitalalbum.viewmodels.AlbumFormViewModel
import kotlinx.android.synthetic.main.album_form_fragment.*
import java.util.*

class AlbumFormFragment : Fragment() {

    private lateinit var viewModel: AlbumFormViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.album_form_fragment, container, false)
        viewModel =
                ViewModelProviders.of(
                    this,
                    activity?.application?.let { Injectors.provideAlbumFormViewModelFactory(it) })
                    .get(AlbumFormViewModel::class.java)
        viewModel.tile().observe(this, Observer { title -> album_title.setText(title) })


        return layout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        create_album_btn.setOnClickListener {
            viewModel.addAlbum(
                Album(
                    "0",
                    album_title.text.toString(),
                    album_description.text.toString(),
                    Calendar.getInstance()
                )
            )
        }
    }
}