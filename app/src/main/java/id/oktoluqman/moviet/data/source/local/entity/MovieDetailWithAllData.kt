package id.oktoluqman.moviet.data.source.local.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class MovieDetailWithAllData(
    @Embedded val movieDetail: MovieDetailEntity,

    @Relation(
        parentColumn = "movieId",
        entityColumn = "movieGenreId",
        associateBy = Junction(MovieDetailGenreCrossRef::class)
    )
    val genres: List<MovieGenreEntity>,

    @Relation(
        parentColumn = "movieId",
        entityColumn = "crewId",
        associateBy = Junction(MovieDetailCrewCrossRef::class)
    )
    val crews: List<CrewEntity>
)
