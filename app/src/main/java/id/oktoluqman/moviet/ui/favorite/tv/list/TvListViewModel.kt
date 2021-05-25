package id.oktoluqman.moviet.ui.favorite.tv.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import id.oktoluqman.moviet.domain.usecase.TMDBUseCase
import javax.inject.Inject

@HiltViewModel
class TvListViewModel @Inject constructor(useCase: TMDBUseCase) : ViewModel() {
    val flow = useCase.getAllFavoriteTvs().cachedIn(viewModelScope)
}
