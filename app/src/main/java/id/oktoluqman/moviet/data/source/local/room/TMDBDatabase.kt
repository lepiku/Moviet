package id.oktoluqman.moviet.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import id.oktoluqman.moviet.data.source.local.entity.MovieItemEntity

@Database(
    entities = [MovieItemEntity::class],
    version = 1,
    exportSchema = false
)
abstract class TMDBDatabase : RoomDatabase() {
    abstract fun MovieDao(): MovieDao
}
