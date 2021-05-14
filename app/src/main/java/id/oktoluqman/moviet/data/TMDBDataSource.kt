package id.oktoluqman.moviet.data

import androidx.lifecycle.LiveData
import id.oktoluqman.moviet.data.source.local.entity.MovieDetailWithAllData
import id.oktoluqman.moviet.data.source.local.entity.MovieItem
import id.oktoluqman.moviet.data.source.remote.response.MovieDetailResponse
import id.oktoluqman.moviet.data.source.remote.response.MovieItemResponse
import id.oktoluqman.moviet.data.source.remote.response.TvDetailResponse
import id.oktoluqman.moviet.data.source.remote.response.TvItemResponse

interface TMDBDataSource {
    suspend fun discoverMovies(): List<MovieItemResponse>
    suspend fun discoverTv(): List<TvItemResponse>
    suspend fun getMovie(id: Int): MovieDetailResponse
    suspend fun getTv(id: Int): TvDetailResponse
    fun getAllFavoriteMovies(): LiveData<List<MovieItem>>
    fun getFavoriteMovieDetail(movieId: Int): LiveData<MovieDetailWithAllData>
    fun setMovieFavorite(movie: MovieDetailWithAllData, state: Boolean)
    fun setMovieFavorite(movie: MovieDetailResponse, state: Boolean)
}
