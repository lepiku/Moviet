package id.oktoluqman.moviet.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import id.oktoluqman.moviet.data.*
import id.oktoluqman.moviet.data.source.remote.TMDBRemoteDataSource
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


@ExperimentalCoroutinesApi
class TMDBRepositoryTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesRule = CoroutinesTestRule()

    private val remote = Mockito.mock(TMDBRemoteDataSource::class.java)
    private lateinit var repository: TMDBRepository

    private val dummyMovies = listOf(
        MovieItem(1, "a", "over", "/a.jpg", 8.4f),
        MovieItem(2, "b", "here", "/b.jpg", 7.6f),
    )
    private val dummyTvs = listOf(
        TvItem(1, "a", "over", "/a.jpg", 8.4f),
        TvItem(2, "b", "here", "/b.jpg", 7.6f),
    )
    private val dummyMovie = MovieDetail(
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
        Credits(1, emptyList()),
    )
    private val dummyTv = TvDetail(
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
        Credits(1, emptyList()),
    )

    @Before
    fun setUp() {
        repository = TMDBRepository(remote)
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
}