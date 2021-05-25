package id.oktoluqman.moviet.ui.home.tv.list

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
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class TvListViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesRule = CoroutinesTestRule()

    private lateinit var viewModel: TvListViewModel

    private val useCase = Mockito.mock(TMDBUseCase::class.java)

    @Before
    fun setUp() {
        Mockito.`when`(useCase.discoverTv()).thenReturn(flowOf(PagingData.from(emptyList())))
        viewModel = TvListViewModel(useCase)
    }

    @Test
    fun queryItemList() {
        coroutinesRule.testDispatcher.runBlockingTest {
            viewModel.flow.first()
            Mockito.verify(useCase).discoverTv()
        }
    }
}
