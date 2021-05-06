package id.oktoluqman.moviet.ui.tv.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.oktoluqman.moviet.data.TvItem
import id.oktoluqman.moviet.services.TMDBRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvListViewModel @Inject constructor(
    private val repository: TMDBRepository
) : ViewModel() {
    private val tvList = MutableLiveData<List<TvItem>>()

    fun queryItemList() {
        viewModelScope.launch {
            tvList.postValue(repository.discoverTv())
        }
    }

    fun getItemList(): LiveData<List<TvItem>> = tvList
}