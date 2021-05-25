package id.oktoluqman.moviet.ui.home.tv.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import id.oktoluqman.moviet.core.domain.usecase.TMDBUseCase
import javax.inject.Inject

@HiltViewModel
class TvListViewModel @Inject constructor(useCase: TMDBUseCase) : ViewModel() {
    val flow = useCase.discoverTv().cachedIn(viewModelScope)
}
