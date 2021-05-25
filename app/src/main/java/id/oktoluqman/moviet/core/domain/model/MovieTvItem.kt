package id.oktoluqman.moviet.core.domain.model

data class MovieTvItem(
    val id: Int,
    val name: String,
    val overview: String,
    val posterPath: String,
    val type: ItemType,
)
