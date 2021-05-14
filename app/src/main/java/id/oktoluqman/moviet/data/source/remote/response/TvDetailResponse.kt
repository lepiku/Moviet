package id.oktoluqman.moviet.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvDetailResponse(
    val id: Int,
    val name: String,
    val overview: String,
    val genres: List<GenreResponse>,
    val status: String,
    val popularity: Float,
    @SerializedName("created_by")
    val createdBy: List<CreatorResponse>,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("vote_average")
    val voteAverage: Float,
    @SerializedName("vote_count")
    val voteCount: Int,
    @SerializedName("first_air_date")
    val firstAirDate: String,
    @SerializedName("last_air_date")
    val lastAirDate: String,
    val credits: CreditsResponse,
)
