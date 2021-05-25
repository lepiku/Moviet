package id.oktoluqman.moviet.core.domain.usecase

import id.oktoluqman.moviet.core.domain.model.MovieDetail
import id.oktoluqman.moviet.core.domain.model.TvDetail
import id.oktoluqman.moviet.core.domain.repository.TMDBDataSource
import javax.inject.Inject

class TMDBInteractor @Inject constructor(private val repository: TMDBDataSource) : TMDBUseCase {
    override fun discoverMovies() = repository.discoverMovies()
    override fun discoverTv() = repository.discoverTv()
    override suspend fun getMovie(id: Int) = repository.getMovie(id)
    override suspend fun getTv(id: Int) = repository.getTv(id)

    override fun getAllFavoriteMovies() = repository.getAllFavoriteMovies()
    override fun isFavoriteMovieById(movieId: Int) = repository.isFavoriteMovieById(movieId)
    override fun setMovieFavorite(movie: MovieDetail, state: Boolean) =
        repository.setMovieFavorite(movie, state)

    override fun getAllFavoriteTvs() = repository.getAllFavoriteTvs()
    override fun isFavoriteTvById(tvId: Int) = repository.isFavoriteTvById(tvId)
    override fun setTvFavorite(tv: TvDetail, state: Boolean) =
        repository.setTvFavorite(tv, state)
}
