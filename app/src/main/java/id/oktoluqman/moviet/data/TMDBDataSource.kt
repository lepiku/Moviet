package id.oktoluqman.moviet.data

import androidx.lifecycle.LiveData
import id.oktoluqman.moviet.data.source.local.entity.MovieItemEntity
import id.oktoluqman.moviet.data.source.remote.response.MovieDetailResponse
import id.oktoluqman.moviet.data.source.remote.response.MovieItemResponse
import id.oktoluqman.moviet.data.source.remote.response.TvDetailResponse
import id.oktoluqman.moviet.data.source.remote.response.TvItemResponse

interface TMDBDataSource {
    suspend fun discoverMovies(): List<MovieItemResponse>
    suspend fun discoverTv(): List<TvItemResponse>
    suspend fun getMovie(id: Int): MovieDetailResponse
    suspend fun getTv(id: Int): TvDetailResponse
    fun getAllFavoriteMovies(): LiveData<List<MovieItemEntity>>
    fun setMovieFavorite(movie: MovieDetailResponse, state: Boolean)
}
