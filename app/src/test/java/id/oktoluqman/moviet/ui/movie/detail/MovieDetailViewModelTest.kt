package id.oktoluqman.moviet.ui.movie.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import id.oktoluqman.moviet.data.source.remote.response.CreditsResponse
import id.oktoluqman.moviet.data.source.remote.response.MovieDetailResponse
import id.oktoluqman.moviet.data.TMDBRepository
import id.oktoluqman.moviet.utils.CoroutinesTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
class MovieDetailViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesRule = CoroutinesTestRule()

    private lateinit var viewModel: MovieDetailViewModel
    private val repository = mock(TMDBRepository::class.java)

    @Before
    fun setUp() {
        viewModel = MovieDetailViewModel(repository)
    }

    @Test
    fun setMovie() {
        coroutinesRule.testDispatcher.runBlockingTest {
            val movie = MovieDetailResponse(
                1,
                "",
                "",
                emptyList(),
                "",
                0.2f,
                "a.jpg",
                0.2f,
                100,
                "",
                2000000,
                CreditsResponse(1, emptyList()),
            )
            `when`(repository.getMovie(1)).thenReturn(movie)

            viewModel.setMovie(1)

            Mockito.verify(repository, Mockito.times(1)).getMovie(1)
            assertEquals(viewModel.getMovie().value, movie)
        }
    }
}
