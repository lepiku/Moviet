package id.oktoluqman.moviet.data

import com.google.gson.annotations.SerializedName

data class DiscoverQuery<T>(
    val page: Int,
    val results: List<T>,
    @SerializedName("total_results")
    val totalResults: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
)