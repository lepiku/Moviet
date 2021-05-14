package id.oktoluqman.moviet.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DiscoverResponse<T>(
    val page: Int,
    val results: List<T>,
    @SerializedName("total_results")
    val totalResults: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
)
