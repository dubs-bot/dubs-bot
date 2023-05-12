package dev.vrba.dubs.bot.repository

import dev.vrba.dubs.bot.domain.UserScoreMatch
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserScoreMatchRepository : CoroutineCrudRepository<UserScoreMatch, Int>