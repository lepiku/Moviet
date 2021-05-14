package id.oktoluqman.moviet.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import id.oktoluqman.moviet.data.source.local.entity.*

@Database(
    entities = [
        MovieDetailEntity::class,
        MovieGenreEntity::class,
        CrewEntity::class,
        MovieDetailGenreCrossRef::class,
        MovieDetailCrewCrossRef::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class TMDBDatabase : RoomDatabase() {
    abstract fun MovieDao(): MovieDao
}
