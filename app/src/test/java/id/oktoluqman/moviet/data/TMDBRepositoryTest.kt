package id.oktoluqman.moviet.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingSource
import id.oktoluqman.moviet.data.source.local.TMDBLocalDataSource
import id.oktoluqman.moviet.data.source.local.entity.MovieItemEntity
import id.oktoluqman.moviet.data.source.local.entity.TvItemEntity
import id.oktoluqman.moviet.data.source.remote.TMDBRemoteDataSource
import id.oktoluqman.moviet.data.source.remote.response.*
import id.oktoluqman.moviet.utils.AppExecutors
import id.oktoluqman.moviet.utils.CoroutinesTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


@ExperimentalCoroutinesApi
class TMDBRepositoryTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesRule = CoroutinesTestRule()

    private val remote = Mockito.mock(TMDBRemoteDataSource::class.java)
    private val local = Mockito.mock(TMDBLocalDataSource::class.java)
    private val appExecutors = Mockito.mock(AppExecutors::class.java)
    private lateinit var repository: TMDBRepository

    private val dummyMovies = listOf(
        MovieItemResponse(1, "a", "over", "/a.jpg", 8.4f),
        MovieItemResponse(2, "b", "here", "/b.jpg", 7.6f),
    )
    private val dummyTvs = listOf(
        TvItemResponse(1, "a", "over", "/a.jpg", 8.4f),
        TvItemResponse(2, "b", "here", "/b.jpg", 7.6f),
    )
    private val dummyMovie = MovieDetailResponse(
        1,
        "",
        "",
        emptyList(),
        "",
        0.2f,
        "a.jpg",
        0.2f,
        100,
        "",
        2000000,
        CreditsResponse(1, emptyList()),
    )
    private val dummyTv = TvDetailResponse(
        1,
        "",
        "",
        emptyList(),
        "",
        0.2f,
        emptyList(),
        "a.jpg",
        0.2f,
        100,
        "",
        "",
        CreditsResponse(1, emptyList()),
    )

    @Before
    fun setUp() {
        repository = TMDBRepository(remote, local, appExecutors)
    }

    @Test
    fun discoverMovies() {
        coroutinesRule.testDispatcher.runBlockingTest {
            `when`(remote.discoverMovies()).thenReturn(dummyMovies)

            val result = repository.discoverMovies()

            verify(remote).discoverMovies()
            assertEquals(dummyMovies, result)
        }
    }

    @Test
    fun discoverTv() {
        coroutinesRule.testDispatcher.runBlockingTest {
            `when`(remote.discoverTv()).thenReturn(dummyTvs)

            val result = repository.discoverTv()

            verify(remote).discoverTv()
            assertEquals(dummyTvs, result)
        }
    }

    @Test
    fun getMovie() {
        coroutinesRule.testDispatcher.runBlockingTest {
            `when`(remote.getMovie(1024)).thenReturn(dummyMovie)

            val result = repository.getMovie(1024)

            verify(remote).getMovie(1024)
            assertEquals(dummyMovie, result)
        }
    }

    @Test
    fun getTv() {
        coroutinesRule.testDispatcher.runBlockingTest {
            `when`(remote.getTv(2048)).thenReturn(dummyTv)

            val result = repository.getTv(2048)

            verify(remote).getTv(2048)
            assertEquals(dummyTv, result)
        }
    }

    @Test
    fun getAllFavoriteMovies() {
        val pagingSource = Mockito.mock(PagingSource::class.java) as PagingSource<Int, MovieItemEntity>
        `when`(local.getAllFavoriteMovies()).thenReturn(pagingSource)

        val result = repository.getAllFavoriteMovies()

        verify(local).getAllFavoriteMovies()
        assertEquals(pagingSource, result)
    }

    @Test
    fun isFavoriteMovieById() {
        val dummyFavorite = MutableLiveData<Boolean>()
        dummyFavorite.value = true
        `when`(local.isFavoriteMovie(1)).thenReturn(dummyFavorite)

        val result = repository.isFavoriteMovieById(1)

        verify(local).isFavoriteMovie(1)
        assertEquals(dummyFavorite, result)
    }

    @Test
    fun setMovieFavorite() {
        val executor = Executors.newSingleThreadExecutor()
        `when`(appExecutors.diskIO()).thenReturn(executor)

        repository.setMovieFavorite(dummyMovie, true)
        executor.awaitTermination(100, TimeUnit.MILLISECONDS)

        val movieEntity = MovieItemEntity(
            movieId = dummyMovie.id,
            title = dummyMovie.title,
            overview = dummyMovie.overview,
            posterPath = dummyMovie.posterPath,
            favorite = true
        )
        verify(local).insertMovie(movieEntity)
    }

    @Test
    fun getAllFavoriteTvs() {
        val pagingSource = Mockito.mock(PagingSource::class.java) as PagingSource<Int, TvItemEntity>
        `when`(local.getAllFavoriteTvs()).thenReturn(pagingSource)

        val result = repository.getAllFavoriteTvs()

        verify(local).getAllFavoriteTvs()
        assertEquals(pagingSource, result)
    }

    @Test
    fun isFavoriteTvById() {
        val dummyFavorite = MutableLiveData<Boolean>()
        dummyFavorite.value = true
        `when`(local.isFavoriteTv(1)).thenReturn(dummyFavorite)

        val result = repository.isFavoriteTvById(1)

        verify(local).isFavoriteTv(1)
        assertEquals(dummyFavorite, result)
    }

    @Test
    fun setTvFavorite() {
        val executor = Executors.newSingleThreadExecutor()
        `when`(appExecutors.diskIO()).thenReturn(executor)

        repository.setTvFavorite(dummyTv, true)
        executor.awaitTermination(100, TimeUnit.MILLISECONDS)

        val tvEntity = TvItemEntity(
            tvId = dummyTv.id,
            name = dummyTv.name,
            overview = dummyTv.overview,
            posterPath = dummyTv.posterPath,
            favorite = true
        )
        verify(local).insertTv(tvEntity)
    }
}
