package dev.vrba.dubs.bot.service

import dev.vrba.dubs.bot.domain.Guild
import dev.vrba.dubs.bot.repository.GuildRepository
import kotlinx.coroutines.flow.toList
import org.springframework.stereotype.Service

@Service
class GuildService(
    private val guildRepository: GuildRepository
) {
    suspend fun getAllGuilds(): List<Guild> {
        return guildRepository.findAll().toList()
    }
}