package id.oktoluqman.moviet.ui.favorite.movie.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingSource
import androidx.paging.PagingState
import id.oktoluqman.moviet.data.TMDBRepository
import id.oktoluqman.moviet.data.source.local.entity.MovieItemEntity
import id.oktoluqman.moviet.utils.CoroutinesTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
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
    private val repository = Mockito.mock(TMDBRepository::class.java)

    @Before
    fun setUp() {
        viewModel = MovieListViewModel(repository)
    }

    @Test
    fun getFlow() {
        coroutinesRule.testDispatcher.runBlockingTest {
            `when`(repository.getAllFavoriteMovies()).thenReturn(object :
                PagingSource<Int, MovieItemEntity>() {
                override fun getRefreshKey(state: PagingState<Int, MovieItemEntity>): Int? {
                    return state.anchorPosition?.let { anchorPosition ->
                        val anchorPage = state.closestPageToPosition(anchorPosition)
                        anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
                    }
                }

                override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieItemEntity> {
                    return LoadResult.Page(
                        data = listOf(),
                        prevKey = null,
                        nextKey = null
                    )
                }
            })

            viewModel.flow.first()
            verify(repository).getAllFavoriteMovies()
        }
    }
}
