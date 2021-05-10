package id.oktoluqman.moviet.ui.movie.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.oktoluqman.moviet.data.MovieDetail
import id.oktoluqman.moviet.data.source.TMDBRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val repository: TMDBRepository
) : ViewModel() {

    private val movie = MutableLiveData<MovieDetail>()

    fun setMovie(id: Int) {
        viewModelScope.launch {
            movie.postValue(repository.getMovie(id))
        }
    }

    fun getMovie(): LiveData<MovieDetail> = movie
}