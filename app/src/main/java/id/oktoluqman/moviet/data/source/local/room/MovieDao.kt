package id.oktoluqman.moviet.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.oktoluqman.moviet.data.source.local.entity.MovieItemEntity

@Dao
interface MovieDao {
    @Query("SELECT * FROM MovieItemEntity WHERE favorite = 1")
    fun getAllFavorites(): LiveData<List<MovieItemEntity>>

    @Query("SELECT EXISTS(SELECT movieId FROM MovieItemEntity WHERE movieId = :movieId AND favorite = 1)")
    fun isFavoriteById(movieId: Int): LiveData<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: MovieItemEntity)
}
