package id.oktoluqman.moviet.data

import androidx.lifecycle.LiveData
import id.oktoluqman.moviet.data.source.local.TMDBLocalDataSource
import id.oktoluqman.moviet.data.source.local.entity.MovieItemEntity
import id.oktoluqman.moviet.data.source.remote.TMDBRemoteDataSource
import id.oktoluqman.moviet.data.source.remote.response.MovieDetailResponse
import id.oktoluqman.moviet.data.source.remote.response.MovieItemResponse
import id.oktoluqman.moviet.data.source.remote.response.TvDetailResponse
import id.oktoluqman.moviet.data.source.remote.response.TvItemResponse
import id.oktoluqman.moviet.utils.AppExecutors
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

    override suspend fun getMovie(id: Int): MovieDetailResponse {
        return remoteDataSource.getMovie(id)
    }

    override suspend fun getTv(id: Int): TvDetailResponse {
        return remoteDataSource.getTv(id)
    }

    override fun getAllFavoriteMovies(): LiveData<List<MovieItemEntity>> {
        return localDataSource.getAllMovies()
    }

    override fun isFavoriteMovieById(movieId: Int): LiveData<Boolean> {
        return localDataSource.isFavoriteMovie(movieId)
    }

    override fun setMovieFavorite(movie: MovieDetailResponse, state: Boolean) {
        appExecutors.diskIO().execute {
            val movieEntity = MovieItemEntity(
                movieId = movie.id,
                title = movie.title,
                overview = movie.overview,
                posterPath = movie.posterPath,
                favorite = state
            )
            localDataSource.insertMovie(movieEntity)
        }
    }
}
