package dev.vrba.dubs.bot.dto

import dev.vrba.dubs.bot.domain.UserScoreMatch

data class UserScoreMatchDto(
    val id: String,
    val guild: String,
    val count: Long,
    val pattern: String,
)

fun UserScoreMatch.toDto(): UserScoreMatchDto {
    return UserScoreMatchDto(
        user.toString(),
        guild.toString(),
        count.toLong(),
        pattern,
    )
}