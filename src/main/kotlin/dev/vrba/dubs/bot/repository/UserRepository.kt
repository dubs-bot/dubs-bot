package dev.vrba.dubs.bot.repository

import dev.vrba.dubs.bot.domain.User
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import java.math.BigInteger

@Repository
interface UserRepository : CoroutineCrudRepository<User, Int> {

    suspend fun findByUser(id: BigInteger): User?

}