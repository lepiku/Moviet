package id.oktoluqman.moviet.ui.favorite.tv.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingSource
import androidx.paging.PagingState
import id.oktoluqman.moviet.data.TMDBRepository
import id.oktoluqman.moviet.data.source.local.entity.TvItemEntity
import id.oktoluqman.moviet.utils.CoroutinesTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class TvListViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesRule = CoroutinesTestRule()

    private lateinit var viewModel: TvListViewModel
    private val repository = Mockito.mock(TMDBRepository::class.java)

    @Before
    fun setUp() {
        viewModel = TvListViewModel(repository)
    }

    @Test
    fun getFlow() {
        coroutinesRule.testDispatcher.runBlockingTest {
            Mockito.`when`(repository.getAllFavoriteTvs()).thenReturn(object :
                PagingSource<Int, TvItemEntity>() {
                override fun getRefreshKey(state: PagingState<Int, TvItemEntity>): Int? {
                    return state.anchorPosition?.let { anchorPosition ->
                        val anchorPage = state.closestPageToPosition(anchorPosition)
                        anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
                    }
                }

                override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TvItemEntity> {
                    return LoadResult.Page(
                        data = listOf(),
                        prevKey = null,
                        nextKey = null
                    )
                }
            })

            viewModel.flow.first()
            Mockito.verify(repository).getAllFavoriteTvs()
        }
    }
}
