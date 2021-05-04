package id.oktoluqman.moviet.movie.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.oktoluqman.moviet.data.MovieItem
import id.oktoluqman.moviet.services.MovieRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val movieList = MutableLiveData<List<MovieItem>>()

    fun queryItemList() {
        viewModelScope.launch {
            movieList.postValue(movieRepository.discoverMovies())
        }
    }

    fun getItemList(): LiveData<List<MovieItem>> = movieList
}