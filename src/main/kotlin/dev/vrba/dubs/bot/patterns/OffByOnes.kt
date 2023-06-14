package dev.vrba.dubs.bot.patterns

import dev.kord.x.emoji.Emojis
import dev.vrba.dubs.bot.domain.DigitPattern
import org.springframework.stereotype.Component
import java.math.BigInteger

val offByOnes = DigitPattern(
    key = "off-by-ones",
    name = "Off by ones",
    description = "Matches a digits that's one low or one high from a repeating digits pattern that's quads or better. Unlucky.",
    emoji = Emojis.clown.unicode,
    points = 10
)

@Component
object OffByOnes : DigitPatternsProvider {
    override fun getAvailablePatterns(): List<DigitPattern> {
        return listOf(offByOnes)
    }

    override fun getMatchedPatterns(id: BigInteger): List<DigitPattern> {
        val base = id / BigInteger.TEN
        val last = id.remainder(BigInteger.TEN)

        val plus = base * BigInteger.TEN + (last + BigInteger.ONE).remainder(BigInteger.TEN)
        val minus = base * BigInteger.TEN + (last + BigInteger.TEN - BigInteger.ONE).remainder(BigInteger.TEN)
        val matches = RepeatingDigits.getMatchedPatterns(plus) + RepeatingDigits.getMatchedPatterns(minus)

        return if (matches.any { it.points >= 1000 }) listOf(offByOnes) else emptyList()
    }
}