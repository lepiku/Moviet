package id.oktoluqman.moviet.ui.favorite.tv.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import id.oktoluqman.moviet.core.domain.usecase.TMDBUseCase
import id.oktoluqman.moviet.utils.CoroutinesTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

@ExperimentalCoroutinesApi
class TvListViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesRule = CoroutinesTestRule()

    private lateinit var viewModel: TvListViewModel
    private val useCase = mock(TMDBUseCase::class.java)

    @Before
    fun setUp() {
        `when`(useCase.getAllFavoriteTvs()).thenReturn(flowOf(PagingData.from(emptyList())))
        viewModel = TvListViewModel(useCase)
    }

    @Test
    fun getFlow() {
        coroutinesRule.testDispatcher.runBlockingTest {
            viewModel.flow.first()
            verify(useCase).getAllFavoriteTvs()
        }
    }
}
