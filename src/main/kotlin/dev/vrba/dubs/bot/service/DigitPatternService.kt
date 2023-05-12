package dev.vrba.dubs.bot.service

import dev.vrba.dubs.bot.domain.DigitPattern
import dev.vrba.dubs.bot.repository.DigitPatternRepository
import kotlinx.coroutines.flow.toList
import org.springframework.stereotype.Service

@Service
class DigitPatternService(private val repository: DigitPatternRepository) {

    suspend fun getAllPatterns(): List<DigitPattern> {
        return repository.findAll().toList()
    }

    suspend fun updatePatterns(patterns: List<DigitPattern>) {
        repository.deleteAll()
        repository.saveAll(patterns).toList()
    }

}