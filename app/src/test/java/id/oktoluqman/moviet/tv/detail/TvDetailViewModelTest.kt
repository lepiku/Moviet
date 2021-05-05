package id.oktoluqman.moviet.tv.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import id.oktoluqman.moviet.data.Credits
import id.oktoluqman.moviet.data.TvDetail
import id.oktoluqman.moviet.services.TMDBRepository
import id.oktoluqman.moviet.utils.CoroutinesTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class TvDetailViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesRule = CoroutinesTestRule()

    private lateinit var viewModel: TvDetailViewModel
    private val repository = Mockito.mock(TMDBRepository::class.java)

    @Before
    fun setUp() {
        viewModel = TvDetailViewModel(repository)
    }

    @Test
    fun setTv() {
        coroutinesRule.testDispatcher.runBlockingTest {
            val tv = TvDetail(
                1,
                "",
                "",
                emptyList(),
                "",
                0.2f,
                emptyList(),
                "a.jpg",
                0.2f,
                100,
                "",
                "",
                Credits(1, emptyList()),
            )
            Mockito.`when`(repository.getTv(1)).thenReturn(tv)

            viewModel.setTv(1)

            Mockito.verify(repository, Mockito.times(1)).getTv(1)
            assertEquals(viewModel.getTv().value, tv)
        }
    }
}