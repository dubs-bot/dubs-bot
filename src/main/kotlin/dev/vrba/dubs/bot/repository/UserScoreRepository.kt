package dev.vrba.dubs.bot.repository

import dev.vrba.dubs.bot.domain.UserScore
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import java.math.BigInteger

@Repository
interface UserScoreRepository : CoroutineCrudRepository<UserScore, Int> {

    suspend fun findByUserAndGuild(user: BigInteger, guild: BigInteger): UserScore?

    suspend fun findDistinctByUser(user: BigInteger): Flow<UserScore>

}