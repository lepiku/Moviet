package id.oktoluqman.moviet.ui.movie.detail

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import id.oktoluqman.moviet.data.TMDBDataSource
import id.oktoluqman.moviet.data.source.remote.response.MovieDetailResponse
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val repository: TMDBDataSource
) : ViewModel() {

    private val movieId = MutableLiveData<Int>()
    private val movie = MutableLiveData<MovieDetailResponse>()
    val favorite: LiveData<Boolean> = Transformations.switchMap(movieId) {
        repository.isFavoriteMovieById(it)
    }

    fun setMovie(id: Int) {
        viewModelScope.launch {
            movieId.postValue(id)
            movie.postValue(repository.getMovie(id))
        }
    }

    fun toggleFavorite() {
        if (movie.value != null)
            repository.setMovieFavorite(movie.value!!, !favorite.value!!)
    }

    fun getMovie(): LiveData<MovieDetailResponse> = movie
}
