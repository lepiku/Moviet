package id.oktoluqman.moviet.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.oktoluqman.moviet.core.data.source.local.entity.TvItemEntity

@Dao
interface TvDao {
    @Query("SELECT * FROM TvItemEntity WHERE favorite = 1")
    fun getAllFavorites(): PagingSource<Int, TvItemEntity>

    @Query("SELECT EXISTS(SELECT tvId FROM TvItemEntity WHERE tvId = :tvId AND favorite = 1)")
    fun isFavoriteById(tvId: Int): LiveData<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(tv: TvItemEntity)
}
