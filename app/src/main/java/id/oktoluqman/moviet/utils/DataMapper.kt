package id.oktoluqman.moviet.utils

import id.oktoluqman.moviet.data.source.local.entity.MovieItemEntity
import id.oktoluqman.moviet.data.source.local.entity.TvItemEntity
import id.oktoluqman.moviet.domain.model.ItemType
import id.oktoluqman.moviet.domain.model.MovieTvItem

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
}
