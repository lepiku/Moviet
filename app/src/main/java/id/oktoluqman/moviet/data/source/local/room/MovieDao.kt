package id.oktoluqman.moviet.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import id.oktoluqman.moviet.data.source.local.entity.*

@Dao
interface MovieDao {
    @Query("SELECT * FROM MovieItemEntity WHERE favorite = 1")
    fun getAll(): LiveData<List<MovieItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: MovieItemEntity)
}
