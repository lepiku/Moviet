package id.oktoluqman.moviet.movie.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import id.oktoluqman.moviet.data.MovieItem
import id.oktoluqman.moviet.services.MovieRepository
import id.oktoluqman.moviet.utils.CoroutinesTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

@ExperimentalCoroutinesApi
class MovieListViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesRule = CoroutinesTestRule()

    private lateinit var viewModel: MovieListViewModel

    private val repository = mock(MovieRepository::class.java)

    @Before
    fun setUp() {
        viewModel = MovieListViewModel(repository)
    }

    @Test
    fun queryItemList() {
        coroutinesRule.testDispatcher.runBlockingTest {
            val movieItem = MovieItem(1, "title", "overview", "/a.jpg", 0.2f)
            val list = listOf(movieItem)
            `when`(repository.fetchMovies()).thenReturn(list)

            viewModel.queryItemList()

            verify(repository, times(1)).fetchMovies()
            assertEquals(viewModel.getItemList().value, list)
        }
    }
}