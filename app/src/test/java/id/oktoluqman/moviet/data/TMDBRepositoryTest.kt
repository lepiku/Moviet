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
import id.oktoluqman.moviet.utils.DataMapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class TMDBRepositoryTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesRule = CoroutinesTestRule()

    @Mock
    lateinit var pagingSourceMovie: PagingSource<Int, MovieItemEntity>

    @Mock
    lateinit var pagingSourceTv: PagingSource<Int, TvItemEntity>

    @Mock
    lateinit var remote: TMDBRemoteDataSource

    @Mock
    lateinit var local: TMDBLocalDataSource

    @Mock
    lateinit var appExecutors: AppExecutors

    private lateinit var repository: TMDBRepository

    private val dummyMovies = listOf(
        MovieItemResponse(1, "a", "over", "/a.jpg"),
        MovieItemResponse(2, "b", "here", "/b.jpg"),
    )
    private val dummyTvs = listOf(
        TvItemResponse(1, "a", "over", "/a.jpg"),
        TvItemResponse(2, "b", "here", "/b.jpg"),
    )
    private val dummyMovieResponse = MovieDetailResponse(
        1,
        "",
        "",
        emptyList(),
        "",
        "a.jpg",
        0.2f,
        "",
        2000000,
        CreditsResponse(emptyList()),
    )
    private val dummyMovie = DataMapper.mapResponseToDomain(dummyMovieResponse)
    private val dummyTvResponse = TvDetailResponse(
        1,
        "",
        "",
        emptyList(),
        "",
        emptyList(),
        "a.jpg",
        0.2f,
        "",
        "",
        CreditsResponse(emptyList()),
    )
    private val dummyTv = DataMapper.mapResponseToDomain(dummyTvResponse)

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
            `when`(remote.getMovie(1024)).thenReturn(dummyMovieResponse)

            val result = repository.getMovie(1024)

            verify(remote).getMovie(1024)
            assertEquals(dummyMovie, result)
        }
    }

    @Test
    fun getTv() {
        coroutinesRule.testDispatcher.runBlockingTest {
            `when`(remote.getTv(2048)).thenReturn(dummyTvResponse)

            val result = repository.getTv(2048)

            verify(remote).getTv(2048)
            assertEquals(dummyTv, result)
        }
    }

    @Test
    fun getAllFavoriteMovies() {
        coroutinesRule.testDispatcher.runBlockingTest {
            `when`(local.getAllFavoriteMovies()).thenReturn(pagingSourceMovie)

            repository.getAllFavoriteMovies().take(1).toList()

            verify(local).getAllFavoriteMovies()
        }
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
            movieId = dummyMovieResponse.id,
            title = dummyMovieResponse.title,
            overview = dummyMovieResponse.overview,
            posterPath = dummyMovieResponse.posterPath,
            favorite = true
        )
        verify(local).insertMovie(movieEntity)
    }

    @Test
    fun getAllFavoriteTvs() {
        coroutinesRule.testDispatcher.runBlockingTest {
            `when`(local.getAllFavoriteTvs()).thenReturn(pagingSourceTv)

            repository.getAllFavoriteTvs().take(1).toList()

            verify(local).getAllFavoriteTvs()
        }
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
            tvId = dummyTvResponse.id,
            name = dummyTvResponse.name,
            overview = dummyTvResponse.overview,
            posterPath = dummyTvResponse.posterPath,
            favorite = true
        )
        verify(local).insertTv(tvEntity)
    }
}
