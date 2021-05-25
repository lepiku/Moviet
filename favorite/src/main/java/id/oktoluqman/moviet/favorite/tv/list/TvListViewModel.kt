package id.oktoluqman.moviet.favorite.tv.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import id.oktoluqman.moviet.core.domain.usecase.TMDBUseCase

class TvListViewModel constructor(useCase: TMDBUseCase) : ViewModel() {
    val flow = useCase.getAllFavoriteTvs().cachedIn(viewModelScope)
}
