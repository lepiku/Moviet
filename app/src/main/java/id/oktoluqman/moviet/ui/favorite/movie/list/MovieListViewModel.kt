package id.oktoluqman.moviet.ui.favorite.movie.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import id.oktoluqman.moviet.data.TMDBDataSource
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(private val repository: TMDBDataSource) : ViewModel() {
    val flow = Pager(PagingConfig(pageSize = 4)) {
        repository.getAllFavoriteMovies()
    }.flow.cachedIn(viewModelScope)
}
