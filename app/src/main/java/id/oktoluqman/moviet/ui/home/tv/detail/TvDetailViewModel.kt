package id.oktoluqman.moviet.ui.home.tv.detail

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import id.oktoluqman.moviet.core.domain.model.MovieTvItem
import id.oktoluqman.moviet.core.domain.model.TvDetail
import id.oktoluqman.moviet.core.domain.usecase.TMDBUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvDetailViewModel @Inject constructor(private val useCase: TMDBUseCase) : ViewModel() {

    private val tvItem = MutableLiveData<MovieTvItem>()
    private val tv: MutableLiveData<TvDetail> = MutableLiveData()
    val favorite: LiveData<Boolean> = Transformations.switchMap(tvItem) {
        useCase.isFavoriteTvById(it.id)
    }

    fun setTv(item: MovieTvItem) {
        viewModelScope.launch {
            tvItem.postValue(item)
            tv.postValue(useCase.getTv(item.id))
        }
    }

    fun toggleFavorite() {
        val itemValue = tvItem.value
        val favoriteValue = favorite.value
        if (itemValue != null && favoriteValue != null)
            useCase.setTvFavorite(itemValue, !favoriteValue)
    }

    fun getTv(): LiveData<TvDetail> = tv
}
