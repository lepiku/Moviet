package id.oktoluqman.moviet.data.source.remote

import android.util.Log
import androidx.paging.PagingSource
import id.oktoluqman.moviet.BuildConfig
import id.oktoluqman.moviet.data.source.remote.paging.MoviesPagingSource
import id.oktoluqman.moviet.data.source.remote.paging.TvsPagingSource
import id.oktoluqman.moviet.data.source.remote.response.*
import id.oktoluqman.moviet.services.TMDBService
import retrofit2.Response
import java.net.UnknownHostException
import javax.inject.Inject

class TMDBRemoteDataSource @Inject constructor(private val service: TMDBService) {
    fun discoverMovies(): PagingSource<Int, MovieItemResponse> {
        return MoviesPagingSource(service)
    }

    fun discoverTv(): PagingSource<Int, TvItemResponse> {
        return TvsPagingSource(service)
    }

    suspend fun getMovie(id: Int): MovieDetailResponse {
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
                "/a.jpg",
                0f,
                "",
                0,
                CreditsResponse(listOf())
            )
        } catch (e: NullPointerException) {
            MovieDetailResponse(
                0,
                "No Internet",
                "",
                listOf(),
                "",
                "/a.jpg",
                0f,
                "",
                0,
                CreditsResponse(listOf())
            )
        }
    }

    suspend fun getTv(id: Int): TvDetailResponse {
        return try {
            val response = service.getTv(id, BuildConfig.TMDB_TOKEN)
            return getResponse { response }!!
        } catch (e: UnknownHostException) {
            TvDetailResponse(
                0,
                "No Internet",
                "",
                listOf(),
                "",
                listOf(),
                "/a.jpg",
                0f,
                "",
                ""
            )
        } catch (e: NullPointerException) {
            TvDetailResponse(
                0,
                "No Internet",
                "",
                listOf(),
                "",
                listOf(),
                "/a.jpg",
                0f,
                "",
                ""
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
