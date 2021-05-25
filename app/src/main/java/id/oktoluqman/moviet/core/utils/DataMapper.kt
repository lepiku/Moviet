package id.oktoluqman.moviet.core.utils

import id.oktoluqman.moviet.core.data.source.local.entity.MovieItemEntity
import id.oktoluqman.moviet.core.data.source.local.entity.TvItemEntity
import id.oktoluqman.moviet.core.data.source.remote.response.*
import id.oktoluqman.moviet.core.domain.model.*

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

    fun mapDomainToEntity(input: TvDetail, favorite: Boolean) = TvItemEntity(
        input.tvId,
        input.name,
        input.overview,
        input.posterPath,
        favorite
    )

    fun mapResponseToDomain(input: MovieItemResponse) = MovieTvItem(
        input.id,
        input.title,
        input.overview,
        input.posterPath,
        ItemType.Movie
    )

    fun mapResponseToDomain(input: TvItemResponse) = MovieTvItem(
        input.id,
        input.name,
        input.overview,
        input.posterPath,
        ItemType.Tv
    )

    fun mapResponseToDomain(input: MovieDetailResponse) = MovieDetail(
        movieId = input.id,
        title = input.title,
        overview = input.overview,
        genres = input.genres.map { mapResponseToDomain(it) },
        status = input.status,
        posterPath = input.posterPath,
        voteAverage = input.voteAverage,
        releaseDate = input.releaseDate,
        revenue = input.revenue,
        credits = mapResponseToDomain(input.credits)
    )

    fun mapResponseToDomain(input: TvDetailResponse) = TvDetail(
        tvId = input.id,
        name = input.name,
        overview = input.overview,
        genres = input.genres.map { mapResponseToDomain(it) },
        status = input.status,
        createdBy = input.createdBy.map { mapResponseToDomain(it) },
        posterPath = input.posterPath,
        voteAverage = input.voteAverage,
        firstAirDate = input.firstAirDate,
        lastAirDate = input.lastAirDate,
    )

    private fun mapResponseToDomain(input: GenreResponse) = Genre(input.name)

    private fun mapResponseToDomain(input: CreditsResponse) = Credits(
        crew = input.crew.map {
            Crew(
                it.name,
                it.job
            )
        }
    )

    private fun mapResponseToDomain(input: CreatorResponse) = Creator(input.name)
}
