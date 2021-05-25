package id.oktoluqman.moviet.utils

import id.oktoluqman.moviet.data.source.local.entity.MovieItemEntity
import id.oktoluqman.moviet.data.source.local.entity.TvItemEntity
import id.oktoluqman.moviet.data.source.remote.response.CreditsResponse
import id.oktoluqman.moviet.data.source.remote.response.GenreResponse
import id.oktoluqman.moviet.data.source.remote.response.MovieDetailResponse
import id.oktoluqman.moviet.domain.model.*

object DataMapper {
    fun mapEntityToDomain(input: MovieItemEntity) = MovieTvItem(
        input.movieId,
        input.title,
        input.overview,
        input.posterPath,
        ItemType.Movie,
    )

    fun mapEntityToDomain(input: TvItemEntity) = MovieTvItem(
        input.tvId,
        input.name,
        input.overview,
        input.posterPath,
        ItemType.Tv,
    )

    fun mapDomainToEntity(input: MovieDetail, favorite: Boolean) = MovieItemEntity(
        input.movieId,
        input.title,
        input.overview,
        input.posterPath,
        favorite
    )

    fun mapResponseToDomain(input: MovieDetailResponse) = MovieDetail(
        input.id,
        input.title,
        input.overview,
        mapResponseToDomain(input.genres),
        input.status,
        input.posterPath,
        input.voteAverage,
        input.releaseDate,
        input.revenue,
        mapResponseToDomain(input.credits)
    )

    private fun mapResponseToDomain(input: List<GenreResponse>) = input.map {
        Genre(
            it.name
        )
    }

    private fun mapResponseToDomain(input: CreditsResponse) = Credits(
        crew = input.crew.map {
            Crew(
                it.name,
                it.job
            )
        }
    )
}
