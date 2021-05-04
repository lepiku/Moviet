package id.oktoluqman.moviet.data

import com.google.gson.annotations.SerializedName

data class TvDetail(
    val id: Int,
    val name: String,
    val overview: String,
    val genres: List<Genre>,
    val status: String,
    val popularity: Float,
    @SerializedName("created_by")
    val createdBy: List<Creator>,
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
    val credits: Credits,
)