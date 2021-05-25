package id.oktoluqman.moviet.ui.tv.detail

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import id.oktoluqman.moviet.domain.repository.TMDBDataSource
import id.oktoluqman.moviet.data.source.remote.response.TvDetailResponse
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvDetailViewModel @Inject constructor(private val repository: TMDBDataSource) : ViewModel() {

    private val tvId = MutableLiveData<Int>()
    private val tv = MutableLiveData<TvDetailResponse>()
    val favorite: LiveData<Boolean> = Transformations.switchMap(tvId) {
        repository.isFavoriteTvById(it)
    }

    fun setTv(id: Int) {
        viewModelScope.launch {
            tvId.postValue(id)
            tv.postValue(repository.getTv(id))
        }
    }

    fun toggleFavorite() {
        if (tv.value != null)
            repository.setTvFavorite(tv.value!!, !favorite.value!!)
    }

    fun getTv(): LiveData<TvDetailResponse> = tv
}
