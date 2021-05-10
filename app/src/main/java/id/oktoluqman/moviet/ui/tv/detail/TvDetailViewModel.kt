package id.oktoluqman.moviet.ui.tv.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.oktoluqman.moviet.data.TvDetail
import id.oktoluqman.moviet.data.source.TMDBDataSource
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvDetailViewModel @Inject constructor(
    private val repository: TMDBDataSource
) : ViewModel() {
    private val tv = MutableLiveData<TvDetail>()

    fun setTv(id: Int) {
        viewModelScope.launch {
            tv.postValue(repository.getTv(id))
        }
    }

    fun getTv(): LiveData<TvDetail> = tv
}