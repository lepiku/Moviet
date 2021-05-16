package id.oktoluqman.moviet.data.source.remote

import android.util.Log
import id.oktoluqman.moviet.BuildConfig
import id.oktoluqman.moviet.data.source.remote.response.*
import id.oktoluqman.moviet.services.TMDBService
import retrofit2.Response
import retrofit2.Retrofit
import java.net.UnknownHostException
import javax.inject.Inject

class TMDBRemoteDataSource @Inject constructor(private val retrofit: Retrofit) {
    suspend fun discoverMovies(): List<MovieItemResponse> {
        Log.d(TAG, "discoverMovies: a")
        val service = retrofit.create(TMDBService::class.java)
        Log.d(TAG, "discoverMovies: b")
        return try {
            val response = service.discoverMovies(BuildConfig.TMDB_TOKEN)
            getResponse { response }!!.results
        } catch (e: UnknownHostException) {
            listOf(MovieItemResponse(0, "No Internet", "", "/a.jpg", 0f))
        } catch (e: NullPointerException) {
            listOf(MovieItemResponse(0, "No result", "", "/a.jpg", 0f))
        }
    }

    suspend fun discoverTv(): List<TvItemResponse> {
        val service = retrofit.create(TMDBService::class.java)
        return try {
            val response = service.discoverTv(BuildConfig.TMDB_TOKEN)
            getResponse { response }!!.results
        } catch (e: UnknownHostException) {
            listOf(TvItemResponse(0, "No Internet", "", "/a.jpg", 0f))
        } catch (e: NullPointerException) {
            listOf(TvItemResponse(0, "No result", "", "/a.jpg", 0f))
        }
    }

    suspend fun getMovie(id: Int): MovieDetailResponse {
        val service = retrofit.create(TMDBService::class.java)
        return try {
            val response = service.getMovie(id, BuildConfig.TMDB_TOKEN, "credits")
            getResponse { response }!!
        } catch (e: UnknownHostException) {
            MovieDetailResponse(
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
                CreditsResponse(0, listOf())
            )
        } catch (e: NullPointerException) {
            MovieDetailResponse(
                0,
                "No Result",
                "",
                listOf(),
                "",
                0f,
                "/a.jpg",
                0f,
                0,
                "",
                0,
                CreditsResponse(0, listOf())
            )
        }
    }

    suspend fun getTv(id: Int): TvDetailResponse {
        val service = retrofit.create(TMDBService::class.java)
        return try {
            val response = service.getTv(id, BuildConfig.TMDB_TOKEN, null)
            return getResponse { response }!!
        } catch (e: UnknownHostException) {
            TvDetailResponse(
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
                CreditsResponse(0, listOf())
            )
        } catch (e: NullPointerException) {
            TvDetailResponse(
                0,
                "No Result",
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
                CreditsResponse(0, listOf())
            )

        }
    }

    private suspend fun <T> getResponse(request: suspend () -> Response<T>): T? {
        return try {
            val result = request.invoke()
            if (result.isSuccessful) {
                result.body()
            } else {
                Log.d(TAG, "getResponse: not success")
                null
            }
        } catch (e: Throwable) {
            Log.d(TAG, "getResponse: error throwable")
            null
        }
    }

    companion object {
        private const val TAG = "TMDBRemoteDataSource"
    }
}
