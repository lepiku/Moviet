package id.oktoluqman.moviet.data.source.local.entity

import androidx.room.Entity

@Entity(primaryKeys = ["movieId", "crewId"])
data class MovieDetailCrewCrossRef(
    val movieId: Int,
    val crewId: Int,
)
