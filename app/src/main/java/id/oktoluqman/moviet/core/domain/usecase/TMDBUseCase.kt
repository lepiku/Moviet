package id.oktoluqman.moviet.core.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import id.oktoluqman.moviet.core.domain.model.MovieDetail
import id.oktoluqman.moviet.core.domain.model.MovieTvItem
import id.oktoluqman.moviet.core.domain.model.TvDetail
import kotlinx.coroutines.flow.Flow

interface TMDBUseCase {
    fun discoverMovies(): Flow<PagingData<MovieTvItem>>
    fun discoverTv(): Flow<PagingData<MovieTvItem>>
    suspend fun getMovie(id: Int): MovieDetail
    suspend fun getTv(id: Int): TvDetail
    fun getAllFavoriteMovies(): Flow<PagingData<MovieTvItem>>
    fun isFavoriteMovieById(movieId: Int): LiveData<Boolean>
    fun setMovieFavorite(movie: MovieTvItem, state: Boolean)
    fun getAllFavoriteTvs(): Flow<PagingData<MovieTvItem>>
    fun isFavoriteTvById(tvId: Int): LiveData<Boolean>
    fun setTvFavorite(tv: TvDetail, state: Boolean)
}
