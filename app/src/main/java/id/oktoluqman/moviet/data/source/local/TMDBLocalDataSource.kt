package id.oktoluqman.moviet.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import id.oktoluqman.moviet.data.source.local.entity.MovieItemEntity
import id.oktoluqman.moviet.data.source.local.entity.TvItemEntity
import id.oktoluqman.moviet.data.source.local.room.MovieDao
import id.oktoluqman.moviet.data.source.local.room.TvDao
import javax.inject.Inject

class TMDBLocalDataSource @Inject constructor(
    private val movieDao: MovieDao,
    private val tvDao: TvDao
) {
    fun getAllFavoriteMovies(): PagingSource<Int, MovieItemEntity> = movieDao.getAllFavorites()

    fun isFavoriteMovie(movieId: Int): LiveData<Boolean> = movieDao.isFavoriteById(movieId)

    fun insertMovie(movie: MovieItemEntity) = movieDao.insert(movie)

    fun getAllFavoriteTvs(): LiveData<List<TvItemEntity>> = tvDao.getAllFavorites()

    fun isFavoriteTv(tvId: Int): LiveData<Boolean> = tvDao.isFavoriteById(tvId)

    fun insertTv(tv: TvItemEntity) = tvDao.insert(tv)
}
