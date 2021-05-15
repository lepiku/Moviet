package id.oktoluqman.moviet.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.oktoluqman.moviet.data.source.local.entity.TvItemEntity

@Dao
interface TvDao {
    @Query("SELECT * FROM TvItemEntity WHERE favorite = 1")
    fun getAllFavorites(): LiveData<List<TvItemEntity>>

    @Query("SELECT EXISTS(SELECT tvId FROM TvItemEntity WHERE tvId = :tvId AND favorite = 1)")
    fun isFavoriteById(tvId: Int): LiveData<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(tv: TvItemEntity)
}
