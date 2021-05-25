package id.oktoluqman.moviet.core.data.source.remote

import androidx.paging.PagingSource
import id.oktoluqman.moviet.core.BuildConfig
import id.oktoluqman.moviet.core.data.source.remote.network.TMDBService
import id.oktoluqman.moviet.core.data.source.remote.paging.MoviesPagingSource
import id.oktoluqman.moviet.core.data.source.remote.paging.TvsPagingSource
import id.oktoluqman.moviet.core.data.source.remote.response.*
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
            service.getMovie(id, BuildConfig.TMDB_TOKEN, "credits")
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
        }
    }

    suspend fun getTv(id: Int): TvDetailResponse {
        return try {
            service.getTv(id, BuildConfig.TMDB_TOKEN)
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
        }
    }
}
