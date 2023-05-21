package dev.vrba.dubs.bot.dto

import dev.vrba.dubs.bot.domain.UserScore

data class UserScoreDto(
    val id: String,
    val guild: String,
    val points: Long
)

fun UserScore.toDto(): UserScoreDto {
    return UserScoreDto(
        user.toString(),
        guild.toString(),
        points.toLong()
    )
}