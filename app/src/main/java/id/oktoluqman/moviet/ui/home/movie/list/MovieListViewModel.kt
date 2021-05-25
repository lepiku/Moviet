package id.oktoluqman.moviet.ui.home.movie.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import id.oktoluqman.moviet.core.domain.usecase.TMDBUseCase
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(useCase: TMDBUseCase) : ViewModel() {
    val flow = useCase.discoverMovies().cachedIn(viewModelScope)
}
