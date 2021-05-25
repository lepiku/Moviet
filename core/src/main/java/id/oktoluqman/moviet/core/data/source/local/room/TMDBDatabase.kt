package id.oktoluqman.moviet.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import id.oktoluqman.moviet.core.data.source.local.entity.MovieItemEntity
import id.oktoluqman.moviet.core.data.source.local.entity.TvItemEntity

@Database(
    entities = [MovieItemEntity::class, TvItemEntity::class],
    version = 1,
    exportSchema = false
)
abstract class TMDBDatabase : RoomDatabase() {
    abstract fun MovieDao(): MovieDao
    abstract fun TvDao(): TvDao
}
