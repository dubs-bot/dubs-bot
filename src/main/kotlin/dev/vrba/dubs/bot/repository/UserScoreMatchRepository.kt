package dev.vrba.dubs.bot.repository

import dev.vrba.dubs.bot.domain.UserScoreMatch
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import java.math.BigInteger

@Repository
interface UserScoreMatchRepository : CoroutineCrudRepository<UserScoreMatch, Int> {

    fun findAllByUserAndGuildAndPatternIn(userId: BigInteger, guildId: BigInteger, patterns: List<String>): Flow<UserScoreMatch>
}