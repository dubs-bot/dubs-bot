package dev.vrba.dubs.bot.dto

import java.math.BigInteger

data class GuildLeaderboardDto(
    val guild: GuildDto,
    val users: Map<String, UserDto>,
    val scores: Map<String, UserScoreDto>,
    val matches: Map<String, Set<UserScoreMatchDto>>
)