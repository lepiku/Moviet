package id.oktoluqman.moviet.ui.tv.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import id.oktoluqman.moviet.domain.model.Credits
import id.oktoluqman.moviet.domain.model.TvDetail
import id.oktoluqman.moviet.domain.usecase.TMDBUseCase
import id.oktoluqman.moviet.utils.CoroutinesTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class TvDetailViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesRule = CoroutinesTestRule()

    @Mock
    private lateinit var favoriteObserver: Observer<Boolean>

    @Mock
    private lateinit var useCase: TMDBUseCase

    private lateinit var viewModel: TvDetailViewModel

    @Before
    fun setUp() {
        viewModel = TvDetailViewModel(useCase)
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
                emptyList(),
                "a.jpg",
                0.2f,
                "",
                ""
            )
            Mockito.`when`(useCase.getTv(1)).thenReturn(tv)

            viewModel.setTv(1)

            Mockito.verify(useCase, Mockito.times(1)).getTv(1)
            assertEquals(viewModel.getTv().value, tv)
        }
    }

    @Test
    fun toggleFavoriteFromFalse() {
        coroutinesRule.testDispatcher.runBlockingTest {
            val tv = TvDetail(
                1,
                "",
                "",
                emptyList(),
                "",
                emptyList(),
                "a.jpg",
                0.2f,
                "",
                ""
            )

            Mockito.`when`(useCase.getTv(1)).thenReturn(tv)
            Mockito.`when`(useCase.isFavoriteTvById(1)).thenReturn(MutableLiveData(false))
            viewModel.favorite.observeForever(favoriteObserver)

            viewModel.setTv(1)

            Mockito.verify(favoriteObserver).onChanged(false)
            Mockito.verify(useCase).isFavoriteTvById(1)

            viewModel.toggleFavorite()
            Mockito.verify(useCase, Mockito.times(1)).setTvFavorite(tv, true)
        }
    }

    @Test
    fun toggleFavoriteFromTrue() {
        coroutinesRule.testDispatcher.runBlockingTest {
            val tv = TvDetail(
                1,
                "",
                "",
                emptyList(),
                "",
                emptyList(),
                "a.jpg",
                0.2f,
                "",
                ""
            )

            Mockito.`when`(useCase.getTv(1)).thenReturn(tv)
            Mockito.`when`(useCase.isFavoriteTvById(1)).thenReturn(MutableLiveData(true))
            viewModel.favorite.observeForever(favoriteObserver)

            viewModel.setTv(1)

            Mockito.verify(favoriteObserver).onChanged(true)
            Mockito.verify(useCase).isFavoriteTvById(1)

            viewModel.toggleFavorite()
            Mockito.verify(useCase, Mockito.times(1)).setTvFavorite(tv, false)
        }
    }
}
