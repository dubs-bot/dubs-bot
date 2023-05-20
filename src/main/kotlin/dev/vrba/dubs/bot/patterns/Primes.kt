package dev.vrba.dubs.bot.patterns

import dev.kord.x.emoji.Emojis
import dev.vrba.dubs.bot.domain.DigitPattern
import org.springframework.stereotype.Component
import java.math.BigInteger


val primes = DigitPattern(
    key = "primes",
    name = "Primes",
    description = "A (highly probable) prime number!",
    emoji = Emojis.nerd.unicode,
    points = 100
)

@Component
class Primes : DigitPatternsProvider {

    override fun getAvailablePatterns(): List<DigitPattern> {
        return listOf(primes)
    }

    override fun getMatchedPatterns(id: BigInteger): List<DigitPattern> {
        return if (id.isProbablePrime(100)) listOf(primes) else emptyList()
    }
}