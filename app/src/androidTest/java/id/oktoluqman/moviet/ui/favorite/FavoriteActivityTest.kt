package id.oktoluqman.moviet.ui.favorite

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import id.oktoluqman.moviet.OkHttpIdlingResourceRule
import id.oktoluqman.moviet.R
import id.oktoluqman.moviet.RecyclerViewItemCountAssertion
import id.oktoluqman.moviet.ui.home.HomeActivity
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class FavoriteActivityTest {
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
    fun favoriteMovie() {
        // tap favorite
        Espresso.onView(ViewMatchers.withContentDescription("MovieListFragment")).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        Thread.sleep(800) // for encrypt db overhead
        Espresso.onView(ViewMatchers.withId(R.id.btn_favorite)).perform(ViewActions.click())

        // navigate to movies FavoriteActivity
        Espresso.pressBack()
        Espresso.onView(ViewMatchers.withId(R.id.action_favorite)).perform(ViewActions.click())

        // after favorite, list goes to 1
        Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.rv_items),
                ViewMatchers.isDisplayed()
            )
        )
        Espresso.onView(ViewMatchers.withContentDescription("MovieListFragment"))
            .check(RecyclerViewItemCountAssertion(1))


        // after un-favorite, list goes to 0
        Espresso.onView(ViewMatchers.withContentDescription("MovieListFragment")).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        Espresso.onView(ViewMatchers.withId(R.id.btn_favorite)).perform(ViewActions.click())
        Espresso.pressBack()
        Espresso.onView(ViewMatchers.withContentDescription("MovieListFragment"))
            .check(RecyclerViewItemCountAssertion(0))
    }

    @Test
    fun favoriteTv() {
        // tap favorite
        Espresso.onView(ViewMatchers.withText("TV SHOWS")).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withContentDescription("TvListFragment")).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        Thread.sleep(800) // for encrypt db overhead
        Espresso.onView(ViewMatchers.withId(R.id.btn_favorite)).perform(ViewActions.click())

        // navigate to tv shows FavoriteActivity
        Espresso.pressBack()
        Espresso.onView(ViewMatchers.withId(R.id.action_favorite)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withText("TV SHOWS")).perform(ViewActions.click())

        // after favorite, list goes to 1
        Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.rv_items),
                ViewMatchers.isDisplayed()
            )
        )
        Espresso.onView(ViewMatchers.withContentDescription("TvListFragment"))
            .check(RecyclerViewItemCountAssertion(1))

        // after un-favorite, list goes to 0
        Espresso.onView(ViewMatchers.withContentDescription("TvListFragment")).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        Espresso.onView(ViewMatchers.withId(R.id.btn_favorite)).perform(ViewActions.click())
        Espresso.pressBack()
        Espresso.onView(ViewMatchers.withContentDescription("TvListFragment"))
            .check(RecyclerViewItemCountAssertion(0))
    }
}
