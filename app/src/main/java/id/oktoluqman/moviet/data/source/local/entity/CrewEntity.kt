package id.oktoluqman.moviet.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CrewEntity(
    @PrimaryKey val crewId: Int,
    val name: String,
    val job: String,
)
