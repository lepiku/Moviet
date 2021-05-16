package id.oktoluqman.moviet.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import id.oktoluqman.moviet.OkHttpIdlingResourceRule
import id.oktoluqman.moviet.R
import org.hamcrest.Matchers.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class HomeActivityTest {
    @get:Rule
    val hiltRule: HiltAndroidRule = HiltAndroidRule(this)

    @get:Rule
    val idlingResourceRule = OkHttpIdlingResourceRule()

    @get:Rule
    val activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun loadListMovies() {
        onView(withContentDescription("MovieListFragment")).check(matches(isDisplayed()))
        onView(withContentDescription("TvListFragment")).check(doesNotExist())
        onView(
            allOf(
                withId(R.id.rv_items),
                isDescendantOfA(withContentDescription("MovieListFragment"))
            )
        )
    }

    @Test
    fun loadListTv() {
        onView(withText("TV SHOWS")).perform(click())
        onView(withContentDescription("MovieListFragment")).check(doesNotExist())
        onView(withContentDescription("TvListFragment")).check(matches(isDisplayed()))
        onView(
            allOf(
                withId(R.id.rv_items),
                isDescendantOfA(withContentDescription("TvListFragment"))
            )
        )
    }

    @Test
    fun loadDetailMovie() {
        onView(withContentDescription("MovieListFragment")).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )
        onView(withId(R.id.img_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_movie_detail_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release_date)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_vote_average)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_director)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_status)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_revenue)).check(matches(isDisplayed()))
    }

    @Test
    fun loadDetailTv() {
        onView(withText("TV SHOWS")).perform(click())
        onView(withContentDescription("TvListFragment")).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )
        onView(withId(R.id.img_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_tv_detail_name)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release_date)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_vote_average)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_creator)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_status)).check(matches(isDisplayed()))
    }
}
