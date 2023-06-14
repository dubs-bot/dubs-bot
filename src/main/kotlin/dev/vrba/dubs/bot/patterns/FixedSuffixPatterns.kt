package dev.vrba.dubs.bot.patterns

import dev.kord.x.emoji.Emojis
import dev.vrba.dubs.bot.domain.DigitPattern
import org.springframework.stereotype.Component
import java.math.BigInteger
import kotlin.math.floor
import kotlin.math.log10
import kotlin.math.pow

val literally1984 = DigitPattern(
    key = "literally-1984",
    name = "Literally 1984",
    description = "A number ending with 1984",
    emoji = Emojis.eye.unicode,
    points = 1000
)

val jackpot = DigitPattern(
    key = "jackpot",
    name = "Jackpot baby!",
    description = "A number ending with 777. Yes this is a nuggy reference.",
    emoji = Emojis.slotMachine.unicode,
    points = 100
)

val blazeIt = DigitPattern(
    key = "420-blaze-it",
    name = "420 Blaze it!",
    description = "A number ending with 420",
    emoji = Emojis.fire.unicode,
    points = 100
)

val jetFuel = DigitPattern(
    key = "jet-fuel",
    name = "Jet fuel can't melt steel beams",
    description = "A number ending with 911",
    emoji = Emojis.airplane.unicode,
    points = 100
)

val funnySexNumber = DigitPattern(
    key = "sixty-nine",
    name = "69",
    description = "Haha funny sex number",
    emoji = Emojis.cancer.unicode,
    points = 10
)

@Component
object FixedSuffixPatterns : DigitPatternsProvider {

    private val suffixes = mapOf(
        69 to funnySexNumber,
        420 to blazeIt,
        777 to jackpot,
        911 to jetFuel,
        1984 to literally1984,
    )

    override fun getAvailablePatterns(): List<DigitPattern> {
        return suffixes.values.toList()
    }

    override fun getMatchedPatterns(id: BigInteger): List<DigitPattern> {
        return suffixes
            .filter { (suffix, _) -> endsWithSuffix(id, suffix) }
            .map { (_, pattern) -> pattern }
    }

    private fun endsWithSuffix(id: BigInteger, suffix: Int): Boolean {
        val base = 10.0
        val length = floor(log10(suffix.toDouble())) + 1
        val divisor = base.pow(length)
        val remainder = id.rem(divisor.toLong().toBigInteger())

        return remainder == suffix.toBigInteger()
    }
}