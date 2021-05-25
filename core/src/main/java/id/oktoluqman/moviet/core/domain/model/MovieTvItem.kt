package id.oktoluqman.moviet.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieTvItem(
    val id: Int,
    val name: String,
    val overview: String,
    val posterPath: String,
    val type: ItemType,
) : Parcelable
