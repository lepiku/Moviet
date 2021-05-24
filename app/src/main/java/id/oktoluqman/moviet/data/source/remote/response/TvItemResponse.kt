package id.oktoluqman.moviet.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvItemResponse(
    val id: Int,
    val name: String,
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String,
)
