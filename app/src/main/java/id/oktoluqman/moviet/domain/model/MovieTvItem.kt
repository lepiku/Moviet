package id.oktoluqman.moviet.domain.model

data class MovieTvItem(
    val id: Int,
    val name: String,
    val overview: String,
    val posterPath: String,
    val type: ItemType,
)
