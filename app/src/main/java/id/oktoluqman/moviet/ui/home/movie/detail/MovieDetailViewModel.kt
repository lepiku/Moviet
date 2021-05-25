package id.oktoluqman.moviet.ui.home.movie.detail

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import id.oktoluqman.moviet.core.domain.model.MovieDetail
import id.oktoluqman.moviet.core.domain.usecase.TMDBUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val useCase: TMDBUseCase) : ViewModel() {

    private val movieId = MutableLiveData<Int>()
    private val movie = MutableLiveData<MovieDetail>()
    val favorite: LiveData<Boolean> = Transformations.switchMap(movieId) {
        useCase.isFavoriteMovieById(it)
    }

    fun setMovie(id: Int) {
        viewModelScope.launch {
            movieId.postValue(id)
            movie.postValue(useCase.getMovie(id))
        }
    }

    fun toggleFavorite() {
        if (movie.value != null)
            useCase.setMovieFavorite(movie.value!!, !favorite.value!!)
    }

    fun getMovie(): LiveData<MovieDetail> = movie
}
