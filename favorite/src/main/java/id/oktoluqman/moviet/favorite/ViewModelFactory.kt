package id.oktoluqman.moviet.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.oktoluqman.moviet.core.domain.usecase.TMDBUseCase
import id.oktoluqman.moviet.favorite.movie.list.MovieListViewModel
import id.oktoluqman.moviet.favorite.tv.list.TvListViewModel
import javax.inject.Inject


class ViewModelFactory @Inject constructor(private val useCase: TMDBUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(MovieListViewModel::class.java) -> {
                MovieListViewModel(useCase) as T
            }
            modelClass.isAssignableFrom(TvListViewModel::class.java) -> {
                TvListViewModel(useCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}
