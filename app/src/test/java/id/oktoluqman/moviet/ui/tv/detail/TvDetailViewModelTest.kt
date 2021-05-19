package id.oktoluqman.moviet.ui.tv.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import id.oktoluqman.moviet.data.TMDBRepository
import id.oktoluqman.moviet.data.source.remote.response.CreditsResponse
import id.oktoluqman.moviet.data.source.remote.response.TvDetailResponse
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

    private lateinit var viewModel: TvDetailViewModel
    private val repository = Mockito.mock(TMDBRepository::class.java)

    @Before
    fun setUp() {
        viewModel = TvDetailViewModel(repository)
    }

    @Test
    fun setTv() {
        coroutinesRule.testDispatcher.runBlockingTest {
            val tv = TvDetailResponse(
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
                CreditsResponse(1, emptyList()),
            )
            Mockito.`when`(repository.getTv(1)).thenReturn(tv)

            viewModel.setTv(1)

            Mockito.verify(repository, Mockito.times(1)).getTv(1)
            assertEquals(viewModel.getTv().value, tv)
        }
    }

    @Test
    fun toggleFavoriteFromFalse() {
        coroutinesRule.testDispatcher.runBlockingTest {
            val tv = TvDetailResponse(
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
                CreditsResponse(1, emptyList()),
            )

            Mockito.`when`(repository.getTv(1)).thenReturn(tv)
            Mockito.`when`(repository.isFavoriteTvById(1)).thenReturn(MutableLiveData(false))
            viewModel.favorite.observeForever(favoriteObserver)

            viewModel.setTv(1)

            Mockito.verify(favoriteObserver).onChanged(false)
            Mockito.verify(repository).isFavoriteTvById(1)

            viewModel.toggleFavorite()
            Mockito.verify(repository, Mockito.times(1)).setTvFavorite(tv, true)
        }
    }

    @Test
    fun toggleFavoriteFromTrue() {
        coroutinesRule.testDispatcher.runBlockingTest {
            val tv = TvDetailResponse(
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
                CreditsResponse(1, emptyList()),
            )

            Mockito.`when`(repository.getTv(1)).thenReturn(tv)
            Mockito.`when`(repository.isFavoriteTvById(1)).thenReturn(MutableLiveData(true))
            viewModel.favorite.observeForever(favoriteObserver)

            viewModel.setTv(1)

            Mockito.verify(favoriteObserver).onChanged(true)
            Mockito.verify(repository).isFavoriteTvById(1)

            viewModel.toggleFavorite()
            Mockito.verify(repository, Mockito.times(1)).setTvFavorite(tv, false)
        }
    }
}
