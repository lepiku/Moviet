package id.oktoluqman.moviet.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieDetailEntity(
    @PrimaryKey val movieId: Int,
    val title: String,
    val overview: String,
    val status: String,
    val popularity: Float,
    val posterPath: String,
    val voteAverage: Float,
    val voteCount: Int,
    val releaseDate: String,
    val revenue: Int,
    var favorite: Boolean,
)
