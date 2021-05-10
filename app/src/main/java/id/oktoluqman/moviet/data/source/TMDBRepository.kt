package id.oktoluqman.moviet.data.source

import id.oktoluqman.moviet.data.MovieDetail
import id.oktoluqman.moviet.data.MovieItem
import id.oktoluqman.moviet.data.TvDetail
import id.oktoluqman.moviet.data.TvItem
import id.oktoluqman.moviet.data.source.remote.TMDBRemoteDataSource
import javax.inject.Inject

class TMDBRepository @Inject constructor(private val remoteDataSource: TMDBRemoteDataSource) :
    TMDBDataSource {

    override suspend fun discoverMovies(): List<MovieItem> {
        return remoteDataSource.discoverMovies()
    }

    override suspend fun discoverTv(): List<TvItem> {
        return remoteDataSource.discoverTv()
    }

    override suspend fun getMovie(id: Int): MovieDetail {
        return remoteDataSource.getMovie(id)
    }

    override suspend fun getTv(id: Int): TvDetail {
        return remoteDataSource.getTv(id)
    }

}