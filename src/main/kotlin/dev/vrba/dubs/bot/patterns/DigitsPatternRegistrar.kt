package dev.vrba.dubs.bot.patterns

import dev.vrba.dubs.bot.service.DigitPatternService
import jakarta.annotation.PostConstruct
import kotlinx.coroutines.runBlocking
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class DigitsPatternRegistrar(
    private val service: DigitPatternService,
    private val providers: List<DigitPatternsProvider>
) {
    private val logger: Logger = LoggerFactory.getLogger(this::class.qualifiedName)

    @PostConstruct
    fun registerAvailablePatterns() {
        val patterns = providers.flatMap { it.getAvailablePatterns() }

        runBlocking {
            logger.info("Registering a total of ${patterns.size} patterns")
            service.updatePatterns(patterns)
        }
    }
}