package dev.vrba.dubs.bot.service

import dev.vrba.dubs.bot.configuration.DiscordConfiguration
import dev.vrba.dubs.bot.domain.DigitPattern
import dev.vrba.dubs.bot.repository.DigitPatternRepository
import kotlinx.coroutines.flow.toList
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitExchangeOrNull

@Service
class DigitPatternService(
    private val repository: DigitPatternRepository,
    private val configuration: DiscordConfiguration,
) {
    private val logger = LoggerFactory.getLogger(this::class.qualifiedName)

    private val client: WebClient = WebClient.create()

    suspend fun getAllPatterns(): List<DigitPattern> {
        return repository.findAll().toList()
    }

    suspend fun updatePatterns(patterns: List<DigitPattern>) {
        repository.deleteAll()
        repository.saveAll(patterns).toList()
    }

    suspend fun invalidateWebISRCache() {
        client.post()
            .uri("https://dubsbot.online/api/invalidate-patterns-cache")
            .header("Api-Key", configuration.apiKey)
            .awaitExchangeOrNull {
                logger.info("Digit patterns ISR cache invalidated!")
                logger.info(it.statusCode().toString())
            }
    }

}