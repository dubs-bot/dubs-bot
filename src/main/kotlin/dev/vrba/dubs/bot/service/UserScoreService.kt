package dev.vrba.dubs.bot.service

import dev.vrba.dubs.bot.domain.*
import dev.vrba.dubs.bot.repository.GuildRepository
import dev.vrba.dubs.bot.repository.UserRepository
import dev.vrba.dubs.bot.repository.UserScoreMatchRepository
import dev.vrba.dubs.bot.repository.UserScoreRepository
import discord4j.rest.util.Image
import kotlinx.coroutines.flow.toList
import org.springframework.stereotype.Service
import java.math.BigInteger
import discord4j.core.`object`.entity.User as DiscordUser
import discord4j.core.`object`.entity.Guild as DiscordGuild

@Service
class UserScoreService(
    private val userRepository: UserRepository,
    private val guildRepository: GuildRepository,
    private val scoreRepository: UserScoreRepository,
    private val matchRepository: UserScoreMatchRepository
) {

    suspend fun updateScore(user: DiscordUser, guild: DiscordGuild, matches: List<DigitPattern>) {
        val userId = user.id.asBigInteger()
        val guildId = guild.id.asBigInteger()

        val score = scoreRepository.findByUserAndGuild(userId, guildId) ?: UserScore(
            user = userId,
            guild = guildId,
            points = BigInteger.ZERO
        )

        val totalPoints = matches.sumOf { it.points }
        val updatedScore = score.copy(points = score.points + BigInteger(totalPoints.toString()))

        scoreRepository.save(updatedScore)

        val patterns = matches.map { it.key }
        val matchInstances = matchRepository.findAllByUserAndGuildAndPatternIn(userId, guildId, patterns).toList()
        val updatedMatches = matches.map { match ->
            val instance = matchInstances.firstOrNull { it.pattern == match.key } ?: UserScoreMatch(
                user = userId,
                guild = guildId,
                pattern = match.key,
                count = 0
            )

            instance.copy(count = instance.count + 1)
        }

        matchRepository.saveAll(updatedMatches).toList()
    }

    suspend fun updateUser(user: DiscordUser) {
        val id = user.id.asBigInteger()
        val instance = userRepository.findByUser(id) ?: User(user = id, name = "", avatar = "")
        val updated = instance.copy(
            name = user.username,
            avatar = (user.getAvatarUrl(Image.Format.PNG).orElse(user.defaultAvatarUrl))
        )

        userRepository.save(updated)
    }

    suspend fun updateGuild(guild: DiscordGuild) {
        val id = guild.id.asBigInteger()
        val instance = guildRepository.findByGuild(id) ?: Guild(guild = id, name = "", icon = "")
        val updated = instance.copy(
            name = guild.name,
            icon = guild.getIconUrl(Image.Format.PNG).orElse("")
        )

        guildRepository.save(updated)
    }

}