package id.oktoluqman.moviet.core.data.source.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import id.oktoluqman.moviet.BuildConfig
import id.oktoluqman.moviet.core.data.source.remote.response.MovieItemResponse
import id.oktoluqman.moviet.core.data.source.remote.network.TMDBService
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
            val body = response.body()

            if (response.isSuccessful && body != null) {
                LoadResult.Page(
                    data = body.results,
                    prevKey = null,
                    nextKey = null
                )
            } else {
                LoadResult.Error(Exception("not successful"))
            }
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

}
