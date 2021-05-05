package id.oktoluqman.moviet.tv.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.oktoluqman.moviet.data.TvItem
import id.oktoluqman.moviet.services.MovieRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvListViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {
    private val tvList = MutableLiveData<List<TvItem>>()

    fun queryItemList() {
        viewModelScope.launch {
            tvList.postValue(movieRepository.discoverTv())
        }
    }

    fun getItemList(): LiveData<List<TvItem>> = tvList
}