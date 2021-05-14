package id.oktoluqman.moviet.data.source.local.entity

import androidx.room.Entity

@Entity(primaryKeys = ["movieId", "movieGenreId"])
data class MovieDetailGenreCrossRef(
    val movieId: Int,
    val movieGenreId: Int,
)
