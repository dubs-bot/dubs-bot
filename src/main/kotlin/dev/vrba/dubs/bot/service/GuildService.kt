package dev.vrba.dubs.bot.service

import dev.vrba.dubs.bot.domain.Guild
import dev.vrba.dubs.bot.repository.GuildRepository
import dev.vrba.dubs.bot.repository.UserScoreRepository
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.flow.toSet
import org.springframework.stereotype.Service
import java.math.BigInteger

@Service
class GuildService(
    private val guildRepository: GuildRepository,
    private val scoreRepository: UserScoreRepository
) {

    suspend fun getGuildsWithUserScore(user: BigInteger): List<Guild> {
        val ids = scoreRepository.findDistinctByUser(user).map { it.guild }.toSet()
        val guilds = guildRepository.findByGuildIn(ids)

        return guilds.toList()
    }
}