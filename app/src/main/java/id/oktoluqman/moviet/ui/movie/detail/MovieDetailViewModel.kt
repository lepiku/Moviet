package id.oktoluqman.moviet.ui.movie.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.oktoluqman.moviet.data.source.remote.response.MovieDetailResponse
import id.oktoluqman.moviet.data.TMDBDataSource
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val repository: TMDBDataSource
) : ViewModel() {

    private val movie = MutableLiveData<MovieDetailResponse>()

    fun setMovie(id: Int) {
        viewModelScope.launch {
            movie.postValue(repository.getMovie(id))
        }
    }

    fun getMovie(): LiveData<MovieDetailResponse> = movie
}
