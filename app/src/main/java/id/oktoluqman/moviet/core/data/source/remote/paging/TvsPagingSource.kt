package id.oktoluqman.moviet.core.data.source.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import id.oktoluqman.moviet.BuildConfig
import id.oktoluqman.moviet.core.data.source.remote.network.TMDBService
import id.oktoluqman.moviet.core.data.source.remote.response.TvItemResponse
import retrofit2.HttpException
import java.io.IOException

class TvsPagingSource(private val service: TMDBService) :
    PagingSource<Int, TvItemResponse>() {
    override fun getRefreshKey(state: PagingState<Int, TvItemResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TvItemResponse> {
        return try {
            val response = service.discoverTv(BuildConfig.TMDB_TOKEN)

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
