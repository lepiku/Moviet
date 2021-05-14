package id.oktoluqman.moviet.data.source.local

import androidx.paging.DataSource
import id.oktoluqman.moviet.data.source.local.entity.MovieDetailWithAllData
import id.oktoluqman.moviet.data.source.local.entity.MovieItem
import id.oktoluqman.moviet.data.source.local.room.MovieDao
import javax.inject.Inject

class TMDBLocalDataSource @Inject constructor(private val movieDao: MovieDao) {
    fun getAllMovies(): DataSource.Factory<Int, MovieItem> = movieDao.getAll()

    fun getMovieDetail(movieId: Int): DataSource.Factory<Int, MovieDetailWithAllData> =
        movieDao.findMovieById(movieId)
}
