package id.oktoluqman.moviet.ui.movie.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.oktoluqman.moviet.data.MovieItem
import id.oktoluqman.moviet.data.source.TMDBDataSource
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val repository: TMDBDataSource
) : ViewModel() {

    private val movieList = MutableLiveData<List<MovieItem>>()

    fun queryItemList() {
        viewModelScope.launch {
            movieList.postValue(repository.discoverMovies())
        }
    }

    fun getItemList(): LiveData<List<MovieItem>> = movieList
}