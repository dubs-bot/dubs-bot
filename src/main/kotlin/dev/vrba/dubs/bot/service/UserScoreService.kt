package dev.vrba.dubs.bot.service

import dev.kord.rest.Image
import dev.vrba.dubs.bot.discord.toBigInteger
import dev.vrba.dubs.bot.domain.*
import dev.vrba.dubs.bot.repository.GuildRepository
import dev.vrba.dubs.bot.repository.UserRepository
import dev.vrba.dubs.bot.repository.UserScoreMatchRepository
import dev.vrba.dubs.bot.repository.UserScoreRepository
import kotlinx.coroutines.flow.toList
import org.springframework.stereotype.Service
import java.math.BigInteger
import dev.kord.core.entity.Guild as KordGuild
import dev.kord.core.entity.User as KordUser

@Service
class UserScoreService(
    private val userRepository: UserRepository,
    private val guildRepository: GuildRepository,
    private val scoreRepository: UserScoreRepository,
    private val matchRepository: UserScoreMatchRepository
) {

    suspend fun updateScore(user: KordUser, guild: KordGuild, matches: List<DigitPattern>) {
        val userId = user.id.toBigInteger()
        val guildId = guild.id.toBigInteger()

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

    suspend fun updateUser(user: KordUser) {
        val id = user.id.toBigInteger()
        val instance = userRepository.findByUser(id) ?: User(user = id, name = "", avatar = "")
        val updated = instance.copy(
            name = user.username,
            avatar = (user.avatar ?: user.defaultAvatar).cdnUrl.toUrl { format = Image.Format.PNG }
        )

        userRepository.save(updated)
    }

    suspend fun updateGuild(guild: KordGuild) {
        val id = guild.id.toBigInteger()
        val instance = guildRepository.findByGuild(id) ?: Guild(guild = id, name = "", icon = "")
        val updated = instance.copy(
            name = guild.name,
            icon = guild.getIconUrl(Image.Format.PNG) ?: ""
        )

        guildRepository.save(updated)
    }

}