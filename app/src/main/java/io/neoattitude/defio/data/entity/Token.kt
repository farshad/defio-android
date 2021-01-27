package io.neoattitude.defio.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Token(
    @PrimaryKey
    val id: Int?,
    val value: String? = null
)
