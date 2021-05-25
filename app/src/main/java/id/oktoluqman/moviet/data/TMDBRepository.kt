package id.oktoluqman.moviet.data

import androidx.lifecycle.LiveData
import androidx.paging.*
import id.oktoluqman.moviet.data.source.local.TMDBLocalDataSource
import id.oktoluqman.moviet.data.source.local.entity.TvItemEntity
import id.oktoluqman.moviet.data.source.remote.TMDBRemoteDataSource
import id.oktoluqman.moviet.data.source.remote.response.MovieDetailResponse
import id.oktoluqman.moviet.data.source.remote.response.MovieItemResponse
import id.oktoluqman.moviet.data.source.remote.response.TvDetailResponse
import id.oktoluqman.moviet.data.source.remote.response.TvItemResponse
import id.oktoluqman.moviet.domain.model.MovieDetail
import id.oktoluqman.moviet.domain.model.MovieTvItem
import id.oktoluqman.moviet.domain.repository.TMDBDataSource
import id.oktoluqman.moviet.utils.AppExecutors
import id.oktoluqman.moviet.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TMDBRepository @Inject constructor(
    private val remoteDataSource: TMDBRemoteDataSource,
    private val localDataSource: TMDBLocalDataSource,
    private val appExecutors: AppExecutors,
) :
    TMDBDataSource {

    override suspend fun discoverMovies(): List<MovieItemResponse> {
        return remoteDataSource.discoverMovies()
    }

    override suspend fun discoverTv(): List<TvItemResponse> {
        return remoteDataSource.discoverTv()
    }

    override suspend fun getMovie(id: Int): MovieDetail{
        return DataMapper.mapResponseToDomain(remoteDataSource.getMovie(id))
    }

    override suspend fun getTv(id: Int): TvDetailResponse {
        return remoteDataSource.getTv(id)
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

    override fun getAllFavoriteTvs(): PagingSource<Int, TvItemEntity> {
        return localDataSource.getAllFavoriteTvs()
    }

    override fun isFavoriteTvById(tvId: Int): LiveData<Boolean> {
        return localDataSource.isFavoriteTv(tvId)
    }

    override fun setTvFavorite(tv: TvDetailResponse, state: Boolean) {
        appExecutors.diskIO().execute {
            val tvEntity = TvItemEntity(
                tvId = tv.id,
                name = tv.name,
                overview = tv.overview,
                posterPath = tv.posterPath,
                favorite = state
            )
            localDataSource.insertTv(tvEntity)
        }
    }
}
