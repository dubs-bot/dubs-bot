package dev.vrba.dubs.bot.patterns

import dev.kord.x.emoji.Emojis
import dev.vrba.dubs.bot.domain.DigitPattern
import org.springframework.stereotype.Component
import java.math.BigInteger

@Component
object RepeatingDigits : DigitPatternsProvider {

    val dubs = DigitPattern(
        key = "dubs",
        name = "Dubs",
        description = "Two repeating digits at the end of the number.",
        emoji = Emojis.two.unicode,
        points = 10
    )

    val trips = DigitPattern(
        key = "trips",
        name = "Trips",
        description = "Three repeating digits at the end of the number.",
        emoji = Emojis.three.unicode,
        points = 10
    )

    val quads = DigitPattern(
        key = "quads",
        name = "Quads",
        description = "Four repeating digits at the end of the number.",
        emoji = Emojis.four.unicode,
        points = 10
    )

    override fun getAvailablePatterns(): List<DigitPattern> {
        return listOf(dubs, trips, quads)
    }

    override fun getMatchedPatterns(id: BigInteger): List<DigitPattern> {
        // TODO
        return emptyList()
    }

}