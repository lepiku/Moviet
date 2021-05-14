package id.oktoluqman.moviet.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import id.oktoluqman.moviet.data.source.local.entity.*

@Dao
interface MovieDao {
    @Query("SELECT title, overview, posterPath FROM MovieDetailEntity")
    fun getAll(): LiveData<List<MovieItem>>

    @Transaction
    @Query("SELECT * FROM MovieDetailEntity WHERE movieId = :movieId")
    fun findMovieById(movieId: Int): LiveData<MovieDetailWithAllData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: MovieDetailEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieGenres(genres: List<MovieGenreEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCrews(crews: List<CrewEntity>)
}
