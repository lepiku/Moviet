package id.oktoluqman.moviet.tv.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.oktoluqman.moviet.data.TvDetail
import id.oktoluqman.moviet.services.MovieRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvDetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {
    private val tv = MutableLiveData<TvDetail>()

    fun setTv(id: Int) {
        viewModelScope.launch {
            tv.postValue(movieRepository.getTv(id))
        }
    }

    fun getTv(): LiveData<TvDetail> = tv
}