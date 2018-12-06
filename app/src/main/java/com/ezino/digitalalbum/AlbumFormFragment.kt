package com.ezino.digitalalbum

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ezino.digitalalbum.di.Injectors
import com.ezino.digitalalbum.viewmodels.AlbumFormViewModel
import kotlinx.android.synthetic.main.album_form_fragment.*

class AlbumFormFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.album_form_fragment, container, false)
        val model =
            ViewModelProviders.of(this, activity?.application?.let { Injectors.provideAlbumFormViewModelFactory(it) })
                .get(AlbumFormViewModel::class.java)
        model.tile().observe(this, Observer { title -> album_title.setText(title) })

        return layout
    }
}