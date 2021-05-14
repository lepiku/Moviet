package id.oktoluqman.moviet.ui.movie.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.oktoluqman.moviet.data.source.remote.response.MovieItemResponse
import id.oktoluqman.moviet.data.TMDBDataSource
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val repository: TMDBDataSource
) : ViewModel() {

    private val movieList = MutableLiveData<List<MovieItemResponse>>()

    fun queryItemList() {
        viewModelScope.launch {
            movieList.postValue(repository.discoverMovies())
        }
    }

    fun getItemList(): LiveData<List<MovieItemResponse>> = movieList
}
