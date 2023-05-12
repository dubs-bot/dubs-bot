package dev.vrba.dubs.bot.repository

import dev.vrba.dubs.bot.domain.DigitPattern
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface DigitPatternRepository : CoroutineCrudRepository<DigitPattern, Int>