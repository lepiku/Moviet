package id.oktoluqman.moviet.data

import id.oktoluqman.moviet.data.source.remote.response.MovieDetailResponse
import id.oktoluqman.moviet.data.source.remote.response.MovieItemResponse
import id.oktoluqman.moviet.data.source.remote.response.TvDetailResponse
import id.oktoluqman.moviet.data.source.remote.response.TvItemResponse
import id.oktoluqman.moviet.data.source.remote.TMDBRemoteDataSource
import javax.inject.Inject

class TMDBRepository @Inject constructor(private val remoteDataSource: TMDBRemoteDataSource) :
    TMDBDataSource {

    override suspend fun discoverMovies(): List<MovieItemResponse> {
        return remoteDataSource.discoverMovies()
    }

    override suspend fun discoverTv(): List<TvItemResponse> {
        return remoteDataSource.discoverTv()
    }

    override suspend fun getMovie(id: Int): MovieDetailResponse {
        return remoteDataSource.getMovie(id)
    }

    override suspend fun getTv(id: Int): TvDetailResponse {
        return remoteDataSource.getTv(id)
    }

}
