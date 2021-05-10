package id.oktoluqman.moviet.data.source.remote

import android.util.Log
import id.oktoluqman.moviet.BuildConfig
import id.oktoluqman.moviet.data.MovieDetail
import id.oktoluqman.moviet.data.MovieItem
import id.oktoluqman.moviet.data.TvDetail
import id.oktoluqman.moviet.data.TvItem
import id.oktoluqman.moviet.services.TMDBService
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class TMDBRemoteDataSource @Inject constructor(private val retrofit: Retrofit) {
    companion object {
        private const val TAG = "TMDBRemoteDataSource"
    }

    suspend fun discoverMovies(): List<MovieItem> {
        val service = retrofit.create(TMDBService::class.java)
        val response = service.discoverMovies(BuildConfig.TMDB_TOKEN)
        return getResponse { response }!!.results
    }

    suspend fun discoverTv(): List<TvItem> {
        val service = retrofit.create(TMDBService::class.java)
        val response = service.discoverTv(BuildConfig.TMDB_TOKEN)
        return getResponse { response }!!.results
    }

    suspend fun getMovie(id: Int): MovieDetail {
        val service = retrofit.create(TMDBService::class.java)
        val response = service.getMovie(id, BuildConfig.TMDB_TOKEN, "credits")
        return getResponse { response }!!
    }

    suspend fun getTv(id: Int): TvDetail {
        val service = retrofit.create(TMDBService::class.java)
        val response = service.getTv(id, BuildConfig.TMDB_TOKEN, null)
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