package id.oktoluqman.moviet.data.source.local

import androidx.lifecycle.LiveData
import id.oktoluqman.moviet.data.source.local.entity.*
import id.oktoluqman.moviet.data.source.local.room.MovieDao
import javax.inject.Inject

class TMDBLocalDataSource @Inject constructor(private val movieDao: MovieDao) {
    fun getAllMovies(): LiveData<List<MovieItemEntity>> = movieDao.getAll()

    fun insertMovie(movie: MovieItemEntity) = movieDao.insertMovie(movie)
}
