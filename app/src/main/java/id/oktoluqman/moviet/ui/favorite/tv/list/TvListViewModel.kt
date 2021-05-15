package id.oktoluqman.moviet.ui.favorite.tv.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import id.oktoluqman.moviet.data.TMDBDataSource
import javax.inject.Inject

@HiltViewModel
class TvListViewModel @Inject constructor(repository: TMDBDataSource) : ViewModel() {
    val flow = Pager(PagingConfig(pageSize = 4)) {
        repository.getAllFavoriteTvs()
    }.flow.cachedIn(viewModelScope)
}
