package id.oktoluqman.moviet.ui.favorite.movie.detail

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import id.oktoluqman.moviet.data.TMDBDataSource
import id.oktoluqman.moviet.data.source.local.entity.MovieDetailWithAllData
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val repository: TMDBDataSource
) : ViewModel() {

    private val movieId = MutableLiveData<Int>()

    fun setMovie(id: Int) {
        viewModelScope.launch {
            movieId.postValue(id)
        }
    }

    fun getMovie(): LiveData<MovieDetailWithAllData> = Transformations.switchMap(movieId) {
        repository.getFavoriteMovieDetail(it)
    }
}
