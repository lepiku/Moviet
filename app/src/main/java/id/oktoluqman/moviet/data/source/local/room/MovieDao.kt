package id.oktoluqman.moviet.data.source.local.room

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import id.oktoluqman.moviet.data.source.local.entity.MovieDetailWithAllData
import id.oktoluqman.moviet.data.source.local.entity.MovieItem

@Dao
interface MovieDao {
    @Query("SELECT title, overview, posterPath FROM MovieDetailEntity")
    fun getAll(): DataSource.Factory<Int, MovieItem>

    @Transaction
    @Query("SELECT * FROM MovieDetailEntity WHERE movieId = :movieId")
    fun findMovieById(movieId: Int): DataSource.Factory<Int, MovieDetailWithAllData>
}
