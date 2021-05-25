package id.oktoluqman.moviet.domain.model

data class TvDetail(
    val tvId: Int,
    val name: String,
    val overview: String,
    val genres: List<Genre>,
    val status: String,
    val createdBy: List<Creator>,
    val posterPath: String,
    val voteAverage: Float,
    val firstAirDate: String,
    val lastAirDate: String,
)
