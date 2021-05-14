package id.oktoluqman.moviet.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import id.oktoluqman.moviet.data.source.local.entity.CrewEntity
import id.oktoluqman.moviet.data.source.local.entity.MovieDetailEntity
import id.oktoluqman.moviet.data.source.local.entity.MovieGenreEntity

@Database(
    entities = [MovieDetailEntity::class, MovieGenreEntity::class, CrewEntity::class],
    version = 1
)
abstract class TMDBDatabase : RoomDatabase() {
    abstract fun MovieDao(): MovieDao
}
