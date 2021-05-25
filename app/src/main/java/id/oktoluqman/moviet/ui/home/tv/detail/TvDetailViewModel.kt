package id.oktoluqman.moviet.ui.home.tv.detail

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import id.oktoluqman.moviet.core.domain.model.TvDetail
import id.oktoluqman.moviet.core.domain.usecase.TMDBUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvDetailViewModel @Inject constructor(private val useCase: TMDBUseCase) : ViewModel() {

    private val tvId = MutableLiveData<Int>()
    private val tv = MutableLiveData<TvDetail>()
    val favorite: LiveData<Boolean> = Transformations.switchMap(tvId) {
        useCase.isFavoriteTvById(it)
    }

    fun setTv(id: Int) {
        viewModelScope.launch {
            tvId.postValue(id)
            tv.postValue(useCase.getTv(id))
        }
    }

    fun toggleFavorite() {
        if (tv.value != null)
            useCase.setTvFavorite(tv.value!!, !favorite.value!!)
    }

    fun getTv(): LiveData<TvDetail> = tv
}
