package com.ezino.digitalalbum.data

import androidx.navigation.findNavController
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import com.ezino.digitalalbum.AlbumCollectionActivity
import com.ezino.digitalalbum.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AlbumFormFragmentTest {
    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(AlbumCollectionActivity::class.java)

    @Before
    fun openAlbumFormFragment() {
        activityTestRule.activity.apply {
            runOnUiThread {
                findNavController(R.id.collection_nav_fragment).navigate(R.id.albumFormFragment)
            }
        }
    }

    @Test
    fun onBackPressed_GotoCollectionFragment() {
        Espresso.onView(ViewMatchers.isRoot()).perform(ViewActions.pressBack())
        Espresso.onView(ViewMatchers.withId(R.id.album_list)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}