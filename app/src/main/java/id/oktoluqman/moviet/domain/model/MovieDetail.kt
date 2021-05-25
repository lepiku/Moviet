package id.oktoluqman.moviet.domain.model

data class MovieDetail(
    val movieId: Int,
    val title: String,
    val overview: String,
    val genres: List<Genre>,
    val status: String,
    val posterPath: String,
    val voteAverage: Float,
    val releaseDate: String,
    val revenue: Int,
    val credits: Credits,
)
