package dev.vrba.dubs.bot.repository

import dev.vrba.dubs.bot.domain.UserScore
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserScoreRepository : CoroutineCrudRepository<UserScore, Int>