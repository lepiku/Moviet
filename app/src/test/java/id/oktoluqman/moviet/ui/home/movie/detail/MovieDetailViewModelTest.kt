package id.oktoluqman.moviet.ui.home.movie.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import id.oktoluqman.moviet.core.domain.model.Credits
import id.oktoluqman.moviet.core.domain.model.MovieDetail
import id.oktoluqman.moviet.core.domain.usecase.TMDBUseCase
import id.oktoluqman.moviet.utils.CoroutinesTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieDetailViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesRule = CoroutinesTestRule()

    @Mock
    private lateinit var favoriteObserver: Observer<Boolean>

    @Mock
    private lateinit var useCase: TMDBUseCase

    private lateinit var viewModel: MovieDetailViewModel

    @Before
    fun setUp() {
        viewModel = MovieDetailViewModel(useCase)
    }

    @Test
    fun setMovie() {
        coroutinesRule.testDispatcher.runBlockingTest {
            val movie = MovieDetail(
                1,
                "",
                "",
                emptyList(),
                "",
                "a.jpg",
                0.2f,
                "",
                2000000,
                Credits(emptyList()),
            )
            `when`(useCase.getMovie(1)).thenReturn(movie)

            viewModel.setMovie(1)

            verify(useCase, times(1)).getMovie(1)
            assertEquals(viewModel.getMovie().value, movie)
        }
    }

    @Test
    fun toggleFavoriteFromFalse() {
        coroutinesRule.testDispatcher.runBlockingTest {
            val movie = MovieDetail(
                1,
                "t",
                "o",
                emptyList(),
                "s",
                "a.jpg",
                0.2f,
                "r",
                2000000,
                Credits(emptyList()),
            )

            `when`(useCase.getMovie(1)).thenReturn(movie)
            `when`(useCase.isFavoriteMovieById(1)).thenReturn(MutableLiveData(false))
            viewModel.favorite.observeForever(favoriteObserver)

            viewModel.setMovie(1)

            verify(favoriteObserver).onChanged(false)
            verify(useCase).isFavoriteMovieById(1)

            viewModel.toggleFavorite()
            verify(useCase, times(1)).setMovieFavorite(movie, true)
        }
    }

    @Test
    fun toggleFavoriteFromTrue() {
        coroutinesRule.testDispatcher.runBlockingTest {
            val movie = MovieDetail(
                1,
                "t",
                "o",
                emptyList(),
                "s",
                "a.jpg",
                0.2f,
                "r",
                2000000,
                Credits(emptyList()),
            )

            `when`(useCase.getMovie(1)).thenReturn(movie)
            `when`(useCase.isFavoriteMovieById(1)).thenReturn(MutableLiveData(true))
            viewModel.favorite.observeForever(favoriteObserver)

            viewModel.setMovie(1)

            verify(favoriteObserver).onChanged(true)
            verify(useCase).isFavoriteMovieById(1)

            viewModel.toggleFavorite()
            verify(useCase, times(1)).setMovieFavorite(movie, false)
        }
    }
}
