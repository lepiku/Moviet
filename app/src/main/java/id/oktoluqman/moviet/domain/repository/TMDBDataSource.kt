package id.oktoluqman.moviet.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import id.oktoluqman.moviet.data.source.remote.response.MovieItemResponse
import id.oktoluqman.moviet.data.source.remote.response.TvItemResponse
import id.oktoluqman.moviet.domain.model.MovieDetail
import id.oktoluqman.moviet.domain.model.MovieTvItem
import id.oktoluqman.moviet.domain.model.TvDetail
import kotlinx.coroutines.flow.Flow

interface TMDBDataSource {
    suspend fun discoverMovies(): List<MovieItemResponse>
    suspend fun discoverTv(): List<TvItemResponse>
    suspend fun getMovie(id: Int): MovieDetail
    suspend fun getTv(id: Int): TvDetail
    fun getAllFavoriteMovies(): Flow<PagingData<MovieTvItem>>
    fun isFavoriteMovieById(movieId: Int): LiveData<Boolean>
    fun setMovieFavorite(movie: MovieDetail, state: Boolean)
    fun getAllFavoriteTvs(): Flow<PagingData<MovieTvItem>>
    fun isFavoriteTvById(tvId: Int): LiveData<Boolean>
    fun setTvFavorite(tv: TvDetail, state: Boolean)
}
