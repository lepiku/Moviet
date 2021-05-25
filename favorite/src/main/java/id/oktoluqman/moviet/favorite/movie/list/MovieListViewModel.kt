package id.oktoluqman.moviet.favorite.movie.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import id.oktoluqman.moviet.core.domain.usecase.TMDBUseCase

class MovieListViewModel constructor(useCase: TMDBUseCase) : ViewModel() {
    val flow = useCase.getAllFavoriteMovies().cachedIn(viewModelScope)
}
