package id.oktoluqman.moviet.core.data

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import id.oktoluqman.moviet.core.data.source.local.TMDBLocalDataSource
import id.oktoluqman.moviet.core.data.source.remote.TMDBRemoteDataSource
import id.oktoluqman.moviet.core.domain.model.MovieDetail
import id.oktoluqman.moviet.core.domain.model.MovieTvItem
import id.oktoluqman.moviet.core.domain.model.TvDetail
import id.oktoluqman.moviet.core.domain.repository.TMDBDataSource
import id.oktoluqman.moviet.core.utils.AppExecutors
import id.oktoluqman.moviet.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TMDBRepository @Inject constructor(
    private val remoteDataSource: TMDBRemoteDataSource,
    private val localDataSource: TMDBLocalDataSource,
    private val appExecutors: AppExecutors,
) :
    TMDBDataSource {

    override fun discoverMovies(): Flow<PagingData<MovieTvItem>> {
        return Pager(PagingConfig(pageSize = 4)) {
            remoteDataSource.discoverMovies()
        }.flow.map { pagingData ->
            pagingData.map { response ->
                DataMapper.mapResponseToDomain(response)
            }
        }
    }

    override fun discoverTv(): Flow<PagingData<MovieTvItem>> {
        return Pager(PagingConfig(pageSize = 4)) {
            remoteDataSource.discoverTv()
        }.flow.map { pagingData ->
            pagingData.map { response ->
                DataMapper.mapResponseToDomain(response)
            }
        }
    }

    override suspend fun getMovie(id: Int): MovieDetail {
        return DataMapper.mapResponseToDomain(remoteDataSource.getMovie(id))
    }

    override suspend fun getTv(id: Int): TvDetail {
        return DataMapper.mapResponseToDomain(remoteDataSource.getTv(id))
    }

    override fun getAllFavoriteMovies(): Flow<PagingData<MovieTvItem>> {
        return Pager(PagingConfig(pageSize = 4)) {
            localDataSource.getAllFavoriteMovies()
        }.flow.map { pagingData ->
            pagingData.map { entity ->
                DataMapper.mapEntityToDomain(entity)
            }
        }
    }

    override fun isFavoriteMovieById(movieId: Int): LiveData<Boolean> {
        return localDataSource.isFavoriteMovie(movieId)
    }

    override fun setMovieFavorite(movie: MovieDetail, state: Boolean) {
        appExecutors.diskIO().execute {
            val movieEntity = DataMapper.mapDomainToEntity(movie, state)
            localDataSource.insertMovie(movieEntity)
        }
    }

    override fun getAllFavoriteTvs(): Flow<PagingData<MovieTvItem>> {
        return Pager(PagingConfig(pageSize = 4)) {
            localDataSource.getAllFavoriteTvs()
        }.flow.map { pagingData ->
            pagingData.map { entity ->
                DataMapper.mapEntityToDomain(entity)
            }
        }
    }

    override fun isFavoriteTvById(tvId: Int): LiveData<Boolean> {
        return localDataSource.isFavoriteTv(tvId)
    }

    override fun setTvFavorite(tv: TvDetail, state: Boolean) {
        appExecutors.diskIO().execute {
            val tvEntity = DataMapper.mapDomainToEntity(tv, state)
            localDataSource.insertTv(tvEntity)
        }
    }
}
