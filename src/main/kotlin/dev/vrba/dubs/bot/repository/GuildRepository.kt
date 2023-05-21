package dev.vrba.dubs.bot.repository

import dev.vrba.dubs.bot.domain.Guild
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import java.math.BigInteger

@Repository
interface GuildRepository : CoroutineCrudRepository<Guild, Int> {

    suspend fun findByGuild(guild: BigInteger): Guild?

    suspend fun findByGuildIn(ids: Set<BigInteger>): Flow<Guild>

}