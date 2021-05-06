package id.oktoluqman.moviet.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import id.oktoluqman.moviet.R
import org.hamcrest.Matchers.*
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Test
    fun loadDetailMovie() {
        onView(allOf(withId(R.id.rv_items), isDisplayed()))
        Thread.sleep(1000)
        onView(withContentDescription("MovieListFragment")).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Thread.sleep(1000)
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
        onView(allOf(withId(R.id.rv_items), isDisplayed()))
        Thread.sleep(500)
        onView(withText("TV SHOWS")).perform(click())
        Thread.sleep(1000)
        onView(withContentDescription("TvListFragment")).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Thread.sleep(1000)
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