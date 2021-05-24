package id.oktoluqman.moviet.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(
    val id: Int,
    val title: String,
    val overview: String,
    val genres: List<GenreResponse>,
    val status: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("vote_average")
    val voteAverage: Float,
    @SerializedName("release_date")
    val releaseDate: String,
    val revenue: Int,
    val credits: CreditsResponse,
)
