package com.ezino.digitalalbum

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import kotlinx.android.synthetic.main.activity_album_collection.*

class AlbumCollectionActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_collection)
//        setSupportActionBar(toolbar)

        val finalHost = NavHostFragment.create(R.navigation.nav_collection)
//        supportFragmentManager.beginTransaction()
//            .add(finalHost, "navigation_host_fragment")
//            .setPrimaryNavigationFragment(finalHost) // this is the equivalent to app:defaultNavHost="true"
//            .commit()
        navController = Navigation.findNavController(this, R.id.collection_nav_fragment)
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
