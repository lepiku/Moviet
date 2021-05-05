package id.oktoluqman.moviet.tv.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import id.oktoluqman.moviet.data.TvItem
import id.oktoluqman.moviet.services.TMDBRepository
import id.oktoluqman.moviet.utils.CoroutinesTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
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
    fun queryItemList() {
        coroutinesRule.testDispatcher.runBlockingTest {
            val movieItem = TvItem(1, "title", "overview", "/a.jpg", 0.2f)
            val list = listOf(movieItem)
            Mockito.`when`(repository.discoverTv()).thenReturn(list)

            viewModel.queryItemList()

            Mockito.verify(repository, Mockito.times(1)).discoverTv()
            assertEquals(viewModel.getItemList().value, list)
        }
    }
}