package com.ezino.digitalalbum

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ezino.digitalalbum.adapters.AlbumAdapter
import com.ezino.digitalalbum.di.Injectors
import com.ezino.digitalalbum.viewmodels.AlbumListViewModel
import com.google.android.material.snackbar.Snackbar

import kotlinx.android.synthetic.main.activity_album_collection.*
import kotlinx.android.synthetic.main.content_album_collection.*

class AlbumCollectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_collection)
        setSupportActionBar(toolbar)

        val factory = Injectors.provideAlbumListViewModelFactory(application)
        val viewModel = ViewModelProviders.of(this, factory).get(AlbumListViewModel::class.java)

        val adapter = AlbumAdapter()
        album_list.adapter = adapter
        viewModel.getAlbums().observe(this, Observer { albums -> if (albums != null) adapter.submitList(albums) })
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_album_collection, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
