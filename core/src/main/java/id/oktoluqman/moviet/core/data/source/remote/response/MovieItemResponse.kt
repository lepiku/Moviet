package id.oktoluqman.moviet.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieItemResponse(
    val id: Int,
    val title: String,
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String,
)
