package dev.vrba.dubs.bot.repository

import dev.vrba.dubs.bot.domain.Guild
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface GuildRepository : CoroutineCrudRepository<Guild, Int>