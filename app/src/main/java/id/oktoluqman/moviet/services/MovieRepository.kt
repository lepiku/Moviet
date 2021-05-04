package id.oktoluqman.moviet.services

import android.util.Log
import id.oktoluqman.moviet.BuildConfig.TMDB_TOKEN
import id.oktoluqman.moviet.data.*
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class MovieRepository @Inject constructor(private val retrofit: Retrofit) {
    companion object {
        private const val TAG = "MovieRepository"
    }

    suspend fun discoverMovies(): List<MovieItem> {
        val service = retrofit.create(MovieService::class.java)
        val response = service.discoverMovies(TMDB_TOKEN)
        return getResponse { response }!!.results
    }

    suspend fun discoverTv(): List<TvItem> {
        val service = retrofit.create(MovieService::class.java)
        val response = service.discoverTv(TMDB_TOKEN)
        return getResponse { response }!!.results
    }

    suspend fun getMovie(id: Int): MovieDetail {
        val service = retrofit.create(MovieService::class.java)
        val response = service.getMovie(id, TMDB_TOKEN, "credits")
        return getResponse { response }!!
    }

    suspend fun getTv(id: Int): TvDetail {
        val service = retrofit.create(MovieService::class.java)
        val response = service.getTv(id, TMDB_TOKEN, null)
        return getResponse { response }!!
    }

    private suspend fun <T> getResponse(request: suspend () -> Response<T>): T? {
        return try {
            val result = request.invoke()
            if (result.isSuccessful) {
                result.body()
            } else {
                Log.d(TAG, "getResponse: not success?")
                null
            }
        } catch (e: Throwable) {
            Log.d(TAG, "getResponse: nani?")

            null
        }
    }
}