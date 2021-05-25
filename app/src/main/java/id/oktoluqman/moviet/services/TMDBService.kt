package id.oktoluqman.moviet.services

import id.oktoluqman.moviet.data.source.remote.response.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBService {
    @GET("discover/movie")
    suspend fun discoverMovies(@Query("api_key") apiKey: String): Response<DiscoverResponse<MovieItemResponse>>

    @GET("discover/tv")
    suspend fun discoverTv(@Query("api_key") apiKey: String): Response<DiscoverResponse<TvItemResponse>>

    @GET("movie/{id}")
    suspend fun getMovie(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String,
        @Query("append_to_response") appendToResponse: String?,
    ): Response<MovieDetailResponse>

    @GET("tv/{id}")
    suspend fun getTv(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String,
    ): Response<TvDetailResponse>
}
