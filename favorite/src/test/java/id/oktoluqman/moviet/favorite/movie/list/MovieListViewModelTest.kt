package id.oktoluqman.moviet.favorite.movie.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import id.oktoluqman.moviet.core.domain.usecase.TMDBUseCase
import id.oktoluqman.moviet.favorite.utils.CoroutinesTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

@ExperimentalCoroutinesApi
class MovieListViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesRule = CoroutinesTestRule()

    private lateinit var viewModel: MovieListViewModel
    private val useCase = Mockito.mock(TMDBUseCase::class.java)

    @Before
    fun setUp() {
        `when`(useCase.getAllFavoriteMovies()).thenReturn(flowOf(PagingData.from(emptyList())))
        viewModel = MovieListViewModel(useCase)
    }

    @Test
    fun getFlow() {
        coroutinesRule.testDispatcher.runBlockingTest {
            viewModel.flow.first()
            verify(useCase).getAllFavoriteMovies()
        }
    }
}
