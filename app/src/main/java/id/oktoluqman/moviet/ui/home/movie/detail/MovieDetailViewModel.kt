package id.oktoluqman.moviet.ui.home.movie.detail

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import id.oktoluqman.moviet.core.domain.model.MovieDetail
import id.oktoluqman.moviet.core.domain.model.MovieTvItem
import id.oktoluqman.moviet.core.domain.usecase.TMDBUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val useCase: TMDBUseCase) : ViewModel() {

    private val movieItem = MutableLiveData<MovieTvItem>()
    private val movie = MutableLiveData<MovieDetail>()
    val favorite: LiveData<Boolean> = Transformations.switchMap(movieItem) {
        useCase.isFavoriteMovieById(it.id)
    }

    fun setMovie(item: MovieTvItem) {
        viewModelScope.launch {
            movieItem.postValue(item)
            movie.postValue(useCase.getMovie(item.id))
        }
    }

    fun toggleFavorite() {
        val value = movieItem.value
        if (value != null)
            useCase.setMovieFavorite(value, !favorite.value!!)
    }

    fun getMovie(): LiveData<MovieDetail> = movie
}
