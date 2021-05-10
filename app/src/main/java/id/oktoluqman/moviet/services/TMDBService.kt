package id.oktoluqman.moviet.services

import id.oktoluqman.moviet.data.*
import id.oktoluqman.moviet.utils.TMDBConstants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBService {
    @GET("discover/movie")
    suspend fun discoverMovies(@Query("api_key") apiKey: String): Response<DiscoverQuery<MovieItem>>

    @GET("discover/tv")
    suspend fun discoverTv(@Query("api_key") apiKey: String): Response<DiscoverQuery<TvItem>>

    @GET("movie/{id}")
    suspend fun getMovie(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String,
        @Query("append_to_response") appendToResponse: String?,
    ): Response<MovieDetail>

    @GET("tv/{id}")
    suspend fun getTv(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String,
        @Query("append_to_response") appendToResponse: String?,
    ): Response<TvDetail>
}