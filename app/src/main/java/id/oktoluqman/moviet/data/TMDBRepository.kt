package id.oktoluqman.moviet.data

import androidx.lifecycle.LiveData
import id.oktoluqman.moviet.data.source.local.TMDBLocalDataSource
import id.oktoluqman.moviet.data.source.local.entity.*
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

    override fun getAllFavoriteMovies(): LiveData<List<MovieItem>> {
        return localDataSource.getAllMovies()
    }

    override fun getFavoriteMovieDetail(movieId: Int): LiveData<MovieDetailWithAllData> {
        return localDataSource.getMovieDetail(movieId)
    }

    override fun setMovieFavorite(movie: MovieDetailWithAllData, state: Boolean) {
        appExecutors.diskIO().execute {
            movie.movieDetail.favorite = state
            localDataSource.insertMovie(movie.movieDetail)
            localDataSource.insertGenres(movie.genres)
            localDataSource.insertCrews(movie.crews)
        }
    }

    override fun setMovieFavorite(movie: MovieDetailResponse, state: Boolean) {
        appExecutors.diskIO().execute {
            val movieEntity = MovieDetailEntity(
                movie.id,
                movie.title,
                movie.overview,
                movie.status,
                movie.popularity,
                movie.posterPath,
                movie.voteAverage,
                movie.voteCount,
                movie.releaseDate,
                movie.revenue,
                state
            )
            localDataSource.insertMovie(movieEntity)

            val genreEntities = movie.genres.map { genre ->
                MovieGenreEntity(genre.id, genre.name)
            }
            localDataSource.insertGenres(genreEntities)

            val crewEntities = movie.credits.crew.map { crew ->
                CrewEntity(crew.id, crew.name, crew.job)
            }
            localDataSource.insertCrews(crewEntities)
        }
    }
}
