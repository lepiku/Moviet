package id.oktoluqman.moviet.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieGenreEntity(
    @PrimaryKey val movieGenreId: Int,
    val name: String,
)
