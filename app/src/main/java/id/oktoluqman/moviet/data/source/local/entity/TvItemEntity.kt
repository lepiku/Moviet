package id.oktoluqman.moviet.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TvItemEntity(
    @PrimaryKey val tvId: Int,
    val name: String,
    val overview: String,
    val posterPath: String,
    val favorite: Boolean,
)
