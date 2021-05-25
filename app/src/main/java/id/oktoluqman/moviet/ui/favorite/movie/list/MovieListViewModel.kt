package id.oktoluqman.moviet.ui.favorite.movie.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import id.oktoluqman.moviet.domain.usecase.TMDBUseCase
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(useCase: TMDBUseCase) : ViewModel() {
    val flow = useCase.getAllFavoriteMovies().cachedIn(viewModelScope)
}
