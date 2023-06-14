package dev.vrba.dubs.bot.patterns

import dev.kord.x.emoji.Emojis
import dev.vrba.dubs.bot.domain.DigitPattern
import dev.vrba.dubs.bot.util.*
import org.springframework.stereotype.Component
import java.math.BigInteger

val dubs = DigitPattern(
    key = "dubs",
    name = "Dubs",
    description = "Two repeating digits at the end of the number.",
    emoji = EmojiTwo,
    points = 10
)

val trips = DigitPattern(
    key = "trips",
    name = "Trips",
    description = "Three repeating digits at the end of the number.",
    emoji = EmojiThree,
    points = 100
)

val quads = DigitPattern(
    key = "quads",
    name = "Quads",
    description = "Four repeating digits at the end of the number.",
    emoji = EmojiFour,
    points = 1_000
)

val pentas = DigitPattern(
    key = "pentas",
    name = "Pentas",
    description = "Five repeating digits at the end of the number.",
    emoji = EmojiFive,
    points = 10_000
)

val sextas = DigitPattern(
    key = "sextas",
    name = "Sextas",
    description = "Six repeating digits at the end of the number.",
    emoji = EmojiSix,
    points = 100_000
)

val septas = DigitPattern(
    key = "septas",
    name = "Septas",
    description = "Seven repeating digits at the end of the number.",
    emoji = EmojiSeven,
    points = 1_000_000
)

val octas = DigitPattern(
    key = "octas",
    name = "Octas",
    description = "Eight repeating digits at the end of the number.",
    emoji = EmojiEight,
    points = 10_000_000
)

val nonas = DigitPattern(
    key = "nonas",
    name = "Nonas",
    description = "Nine repeating digits at the end of the number.",
    emoji = EmojiNine,
    points = 100_000_000
)

val decas = DigitPattern(
    key = "decas",
    name = "Decas",
    description = "Ten repeating digits at the end of the number.",
    emoji = EmojiTen,
    points = 1_000_000_000
)

val over9000 = DigitPattern(
    key = "over-9000",
    name = "IT'S OVER 9000!",
    description = "More than ten repeating digits at the end of the number. What. The. Actual. Fuck????",
    emoji = EmojiInfinity,
    points = 1_000_000_000
)

val clearDigits = DigitPattern(
    key = "clear",
    name = "Clear digits",
    description = "Dubs, trips, quads etc. with zeros as the repeating digits at the end.",
    emoji = EmojiCocktail,
    points = 100
)

val pureDigits = DigitPattern(
    key = "pure",
    name = "Pure digits",
    description = "Dubs, trips, quads etc. where the number of repeated digits matches the digit. Eg. 22, 333, 4444, 5555...",
    emoji = EmojiDove,
    points = 100
)

@Component
object RepeatingDigits : DigitPatternsProvider {

    override fun getAvailablePatterns(): List<DigitPattern> {
        return listOf(
            dubs,
            trips,
            quads,
            pentas,
            sextas,
            septas,
            octas,
            nonas,
            decas,
            over9000,
            clearDigits,
            pureDigits
        )
    }

    override fun getMatchedPatterns(id: BigInteger): List<DigitPattern> {
        val count = countRepeatingDigits(id)
        val base = when (count) {
            1 -> return emptyList()
            2 -> listOf(dubs)
            3 -> listOf(trips)
            4 -> listOf(quads)
            5 -> listOf(pentas)
            6 -> listOf(sextas)
            7 -> listOf(septas)
            8 -> listOf(octas)
            9 -> listOf(nonas)
            10 -> listOf(decas)
            else -> listOf(over9000)
        }

        return base + matchSpecialDigits(count, id)
    }

    private fun matchSpecialDigits(count: Int, id: BigInteger): List<DigitPattern> {
        val pure = count >= 2 && id.mod(BigInteger.TEN) == BigInteger(count.toString())
        val clear = count >= 2 && id.mod(BigInteger.TEN) == BigInteger.ZERO

        return listOfNotNull(
            pureDigits.takeIf { pure },
            clearDigits.takeIf { clear }
        )
    }

    private fun countRepeatingDigits(id: BigInteger): Int {
        val last = id.rem(BigInteger.TEN)
        val sequence = generateSequence(id) { it.div(BigInteger.TEN) }

        return sequence.takeWhile { it.rem(BigInteger.TEN) == last }.count()
    }

}