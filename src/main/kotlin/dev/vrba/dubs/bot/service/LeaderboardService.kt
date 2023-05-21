package dev.vrba.dubs.bot.service

import dev.vrba.dubs.bot.dto.GuildLeaderboardDto
import dev.vrba.dubs.bot.dto.toDto
import dev.vrba.dubs.bot.repository.GuildRepository
import dev.vrba.dubs.bot.repository.UserRepository
import dev.vrba.dubs.bot.repository.UserScoreMatchRepository
import dev.vrba.dubs.bot.repository.UserScoreRepository
import kotlinx.coroutines.flow.toSet
import org.springframework.stereotype.Service
import java.math.BigInteger

@Service
class LeaderboardService(
    private val guildRepository: GuildRepository,
    private val scoreRepository: UserScoreRepository,
    private val matchRepository: UserScoreMatchRepository,
    private val userRepository: UserRepository,
) {

    suspend fun getGuildLeaderboard(guildId: BigInteger): GuildLeaderboardDto? {
        val guild = guildRepository.findByGuild(guildId) ?: return null
        val scores = scoreRepository.findAllByGuild(guildId).toSet()
        val matches = matchRepository.findAllByGuild(guildId).toSet()
        val users = userRepository.findAllByUserIn(scores.map { it.user }).toSet()

        return GuildLeaderboardDto(
            guild.toDto(),
            users.associate { it.user.toString() to it.toDto() },
            scores.associate { it.user.toString() to it.toDto() },
            matches.groupBy { it.user }.entries.associate { (user, matches) ->
                user.toString() to matches.map { it.toDto() }.toSet()
            }
        )
    }
}