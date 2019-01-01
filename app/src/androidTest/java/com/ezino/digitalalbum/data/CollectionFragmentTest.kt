package com.ezino.digitalalbum.data

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import com.ezino.digitalalbum.AlbumCollectionActivity
import com.ezino.digitalalbum.R
import org.junit.Rule
import org.junit.Test

class CollectionFragmentTest {
    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(AlbumCollectionActivity::class.java)

    @Test
    fun onClickAddAlbum() {
        Espresso.onView(ViewMatchers.withId(R.id.fab)).perform(click())

        Espresso.onView(ViewMatchers.withId(R.id.album_title)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}