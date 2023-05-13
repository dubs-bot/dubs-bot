package dev.vrba.dubs.bot.patterns

import dev.kord.x.emoji.Emojis
import dev.vrba.dubs.bot.domain.DigitPattern
import org.springframework.stereotype.Component
import java.math.BigInteger

private val dubs = DigitPattern(
    key = "dubs",
    name = "Dubs",
    description = "Two repeating digits at the end of the number.",
    emoji = Emojis.two.unicode,
    points = 10
)

private val trips = DigitPattern(
    key = "trips",
    name = "Trips",
    description = "Three repeating digits at the end of the number.",
    emoji = Emojis.three.unicode,
    points = 100
)

private val quads = DigitPattern(
    key = "quads",
    name = "Quads",
    description = "Four repeating digits at the end of the number.",
    emoji = Emojis.four.unicode,
    points = 1_000
)

private val pentas = DigitPattern(
    key = "pentas",
    name = "Pentas",
    description = "Five repeating digits at the end of the number.",
    emoji = Emojis.five.unicode,
    points = 10_000
)

private val sextas = DigitPattern(
    key = "sextas",
    name = "Sextas",
    description = "Six repeating digits at the end of the number.",
    emoji = Emojis.six.unicode,
    points = 100_000
)

private val septas = DigitPattern(
    key = "septas",
    name = "Septas",
    description = "Seven repeating digits at the end of the number.",
    emoji = Emojis.seven.unicode,
    points = 1_000_000
)

private val octas = DigitPattern(
    key = "octas",
    name = "Octas",
    description = "Eight repeating digits at the end of the number.",
    emoji = Emojis.eight.unicode,
    points = 10_000_000
)

private val nonas = DigitPattern(
    key = "nonas",
    name = "Nonas",
    description = "Nine repeating digits at the end of the number.",
    emoji = Emojis.nine.unicode,
    points = 100_000_000
)

private val decas = DigitPattern(
    key = "decas",
    name = "Decas",
    description = "Ten repeating digits at the end of the number.",
    emoji = Emojis.keycapTen.unicode,
    points = 1_000_000_000
)

@Component
object RepeatingDigits : DigitPatternsProvider {

    override fun getAvailablePatterns(): List<DigitPattern> {
        return listOf(dubs, trips, quads, pentas, sextas, septas, octas, nonas, decas)
    }

    override fun getMatchedPatterns(id: BigInteger): List<DigitPattern> {
        val count = countRepeatingDigits(id)
        val base = when (count) {
            2 -> listOf(dubs)
            3 -> listOf(trips)
            4 -> listOf(quads)
            5 -> listOf(pentas)
            6 -> listOf(sextas)
            7 -> listOf(septas)
            8 -> listOf(octas)
            9 -> listOf(nonas)
            10 -> listOf(decas)
            else -> emptyList()
        }

        // TODO: Clear digits

        // TODO: Pure digits

        return base
    }

    private fun countRepeatingDigits(id: BigInteger): Int {
        val last = id.rem(BigInteger.TEN)
        val sequence = generateSequence(id) { id.div(BigInteger.TEN) }

        return sequence.takeWhile { it.rem(BigInteger.TEN) == last }.count()
    }

}