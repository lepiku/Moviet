package id.oktoluqman.moviet.data

import com.google.gson.annotations.SerializedName

data class TvItem(
    val id: Int,
    val name: String,
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("vote_average")
    val voteAverage: Float,
)