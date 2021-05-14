package id.oktoluqman.moviet.ui.favorite.movie.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.oktoluqman.moviet.data.TMDBDataSource
import id.oktoluqman.moviet.data.source.local.entity.MovieItem
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(private val repository: TMDBDataSource) : ViewModel() {
    fun getItemList(): LiveData<List<MovieItem>> = repository.getAllFavoriteMovies()
}
