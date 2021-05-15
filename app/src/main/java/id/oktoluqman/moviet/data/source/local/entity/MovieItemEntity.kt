package id.oktoluqman.moviet.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieItemEntity(
    @PrimaryKey val movieId: Int,
    val title: String,
    val overview: String,
    val posterPath: String,
    val favorite: Boolean,
)
