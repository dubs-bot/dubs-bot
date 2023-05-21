package dev.vrba.dubs.bot.repository

import dev.vrba.dubs.bot.domain.User
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import java.math.BigInteger

@Repository
interface UserRepository : CoroutineCrudRepository<User, Int> {

    suspend fun findByUser(id: BigInteger): User?

    fun findAllByUserIn(users: Collection<BigInteger>): Flow<User>

}