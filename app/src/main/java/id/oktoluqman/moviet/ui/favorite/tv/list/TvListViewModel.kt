package id.oktoluqman.moviet.ui.favorite.tv.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.oktoluqman.moviet.data.TMDBDataSource
import id.oktoluqman.moviet.data.source.local.entity.TvItemEntity
import javax.inject.Inject

@HiltViewModel
class TvListViewModel @Inject constructor(repository: TMDBDataSource) : ViewModel() {
    val tvList: LiveData<List<TvItemEntity>> = repository.getAllFavoriteTvs()
}
