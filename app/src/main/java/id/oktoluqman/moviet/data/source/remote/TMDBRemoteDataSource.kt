package id.oktoluqman.moviet.data.source.remote

import android.util.Log
import id.oktoluqman.moviet.BuildConfig
import id.oktoluqman.moviet.data.*
import id.oktoluqman.moviet.services.TMDBService
import retrofit2.Response
import retrofit2.Retrofit
import java.net.UnknownHostException
import javax.inject.Inject

class TMDBRemoteDataSource @Inject constructor(private val retrofit: Retrofit) {
    companion object {
        private const val TAG = "TMDBRemoteDataSource"
    }

    suspend fun discoverMovies(): List<MovieItem> {
        Log.d(TAG, "discoverMovies: a")
        val service = retrofit.create(TMDBService::class.java)
        Log.d(TAG, "discoverMovies: b")
        return try {
            val response = service.discoverMovies(BuildConfig.TMDB_TOKEN)
            getResponse { response }!!.results
        } catch (e: UnknownHostException) {
            listOf(MovieItem(0, "No Internet", "", "/a.jpg", 0f))
        }

    }

    suspend fun discoverTv(): List<TvItem> {
        val service = retrofit.create(TMDBService::class.java)
        return try {
            val response = service.discoverTv(BuildConfig.TMDB_TOKEN)
            getResponse { response }!!.results
        } catch (e: UnknownHostException) {
            listOf(TvItem(0, "No Internet", "", "/a.jpg", 0f))
        }
    }

    suspend fun getMovie(id: Int): MovieDetail {
        val service = retrofit.create(TMDBService::class.java)
        return try {
            val response = service.getMovie(id, BuildConfig.TMDB_TOKEN, "credits")
            getResponse { response }!!
        } catch (e: UnknownHostException) {
            MovieDetail(
                0,
                "No Internet",
                "",
                listOf(),
                "",
                0f,
                "/a.jpg",
                0f,
                0,
                "",
                0,
                Credits(0, listOf())
            )

        }
    }

    suspend fun getTv(id: Int): TvDetail {
        val service = retrofit.create(TMDBService::class.java)
        return try {
            val response = service.getTv(id, BuildConfig.TMDB_TOKEN, null)
            return getResponse { response }!!
        } catch (e: UnknownHostException) {
            TvDetail(
                0,
                "No Internet",
                "",
                listOf(),
                "",
                0f,
                listOf(),
                "/a.jpg",
                0f,
                0,
                "",
                "",
                Credits(0, listOf())
            )

        }
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