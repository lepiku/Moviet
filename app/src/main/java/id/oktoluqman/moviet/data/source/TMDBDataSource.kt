package id.oktoluqman.moviet.data.source

import id.oktoluqman.moviet.data.MovieDetail
import id.oktoluqman.moviet.data.MovieItem
import id.oktoluqman.moviet.data.TvDetail
import id.oktoluqman.moviet.data.TvItem

interface TMDBDataSource {
    suspend fun discoverMovies(): List<MovieItem>
    suspend fun discoverTv(): List<TvItem>
    suspend fun getMovie(id: Int): MovieDetail
    suspend fun getTv(id: Int): TvDetail
}