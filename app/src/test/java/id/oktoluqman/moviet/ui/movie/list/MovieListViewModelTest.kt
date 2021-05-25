package id.oktoluqman.moviet.ui.movie.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import id.oktoluqman.moviet.data.source.remote.response.MovieItemResponse
import id.oktoluqman.moviet.domain.usecase.TMDBUseCase
import id.oktoluqman.moviet.utils.CoroutinesTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
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

    private val useCase = mock(TMDBUseCase::class.java)

    @Before
    fun setUp() {
        `when`(useCase.discoverMovies()).thenReturn(flowOf(PagingData.from(emptyList())))
        viewModel = MovieListViewModel(useCase)
    }

    @Test
    fun queryItemList() {
        coroutinesRule.testDispatcher.runBlockingTest {
            viewModel.flow.first()
            verify(useCase).discoverMovies()
        }
    }
}
