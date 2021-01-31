package io.neoattitude.defio.data.model

data class Challenge(
    val id: Long,
    val title: String?,
    val description: String?,
    val viewCount: Int,
    val participantCount: Int,
    val averageAge: Int,
    val icon: String
)