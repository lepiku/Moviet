package id.oktoluqman.moviet.home

import id.oktoluqman.moviet.data.DiscoverQuery
import id.oktoluqman.moviet.data.MovieDetail
import id.oktoluqman.moviet.data.MovieItem
import id.oktoluqman.moviet.data.TvItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBService {
    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
    }

    @GET("discover/movie")
    fun discoverMovies(@Query("api_key") apiKey: String): Call<DiscoverQuery<MovieItem>>

    @GET("discover/tv")
    fun discoverTv(@Query("api_key") apiKey: String): Call<DiscoverQuery<TvItem>>

    @GET("movie/{id}")
    fun getMovie(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String,
        @Query("append_to_response") appendToResponse: String?,
    ): Call<MovieDetail>
}