package id.oktoluqman.moviet.core.data.source.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import id.oktoluqman.moviet.core.BuildConfig
import id.oktoluqman.moviet.core.data.source.remote.network.TMDBService
import id.oktoluqman.moviet.core.data.source.remote.response.MovieItemResponse
import retrofit2.HttpException
import java.io.IOException

class MoviesPagingSource(private val service: TMDBService) :
    PagingSource<Int, MovieItemResponse>() {
    override fun getRefreshKey(state: PagingState<Int, MovieItemResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieItemResponse> {
        return try {
            val response = service.discoverMovies(BuildConfig.TMDB_TOKEN)

            LoadResult.Page(
                data = response.results,
                prevKey = null,
                nextKey = null
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

}
