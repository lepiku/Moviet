package id.oktoluqman.moviet.data.source.local

import androidx.lifecycle.LiveData
import id.oktoluqman.moviet.data.source.local.entity.*
import id.oktoluqman.moviet.data.source.local.room.MovieDao
import javax.inject.Inject

class TMDBLocalDataSource @Inject constructor(private val movieDao: MovieDao) {
    fun getAllMovies(): LiveData<List<MovieItem>> = movieDao.getAll()

    fun getMovieDetail(movieId: Int): LiveData<MovieDetailWithAllData> =
        movieDao.findMovieById(movieId)

    fun insertMovie(movie: MovieDetailEntity) = movieDao.insertMovie(movie)

    fun insertGenres(genres: List<MovieGenreEntity>) = movieDao.insertMovieGenres(genres)

    fun insertCrews(crews: List<CrewEntity>) = movieDao.insertCrews(crews)
}
