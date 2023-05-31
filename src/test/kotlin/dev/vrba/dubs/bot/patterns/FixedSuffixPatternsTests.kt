package dev.vrba.dubs.bot.patterns

import dev.vrba.dubs.bot.domain.DigitPattern
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigInteger

internal class FixedSuffixPatternsTests {

    @Test
    fun `test that random items do not match`() {
        assertEquals(emptyList<DigitPattern>(), FixedSuffixPatterns.getMatchedPatterns(BigInteger("1058714467799662643")))
        assertEquals(emptyList<DigitPattern>(), FixedSuffixPatterns.getMatchedPatterns(BigInteger("1058714503686139914")))
        assertEquals(emptyList<DigitPattern>(), FixedSuffixPatterns.getMatchedPatterns(BigInteger("1058714504684392550")))
        assertEquals(emptyList<DigitPattern>(), FixedSuffixPatterns.getMatchedPatterns(BigInteger("1058714506802507907")))
        assertEquals(emptyList<DigitPattern>(), FixedSuffixPatterns.getMatchedPatterns(BigInteger("1058714508467650570")))
        assertEquals(emptyList<DigitPattern>(), FixedSuffixPatterns.getMatchedPatterns(BigInteger("1058714524292759552")))
        assertEquals(emptyList<DigitPattern>(), FixedSuffixPatterns.getMatchedPatterns(BigInteger("1058714525781729291")))
        assertEquals(emptyList<DigitPattern>(), FixedSuffixPatterns.getMatchedPatterns(BigInteger("1058714526905806850")))
        assertEquals(emptyList<DigitPattern>(), FixedSuffixPatterns.getMatchedPatterns(BigInteger("1058714528738705408")))
        assertEquals(emptyList<DigitPattern>(), FixedSuffixPatterns.getMatchedPatterns(BigInteger("1058714536439451758")))
        assertEquals(emptyList<DigitPattern>(), FixedSuffixPatterns.getMatchedPatterns(BigInteger("1058714537991348295")))
        assertEquals(emptyList<DigitPattern>(), FixedSuffixPatterns.getMatchedPatterns(BigInteger("1058714539396435998")))
        assertEquals(emptyList<DigitPattern>(), FixedSuffixPatterns.getMatchedPatterns(BigInteger("1058714540759593071")))
        assertEquals(emptyList<DigitPattern>(), FixedSuffixPatterns.getMatchedPatterns(BigInteger("1058714542298890261")))
        assertEquals(emptyList<DigitPattern>(), FixedSuffixPatterns.getMatchedPatterns(BigInteger("1058714543922089984")))
    }

    @Test
    fun `test that 1984 are matched`() {
        val match = listOf(literally1984)

        assertEquals(match, FixedSuffixPatterns.getMatchedPatterns(BigInteger("1058714467799661984")))
        assertEquals(match, FixedSuffixPatterns.getMatchedPatterns(BigInteger("1058714542298891984")))
    }

    @Test
    fun `test that 777 are matched`() {
        val match = listOf(jackpot)

        assertEquals(match, FixedSuffixPatterns.getMatchedPatterns(BigInteger("1058714467799661777")))
        assertEquals(match, FixedSuffixPatterns.getMatchedPatterns(BigInteger("1058714542298891777")))
    }

    @Test
    fun `test that 420 are matched`() {
        val match = listOf(blazeIt)

        assertEquals(match, FixedSuffixPatterns.getMatchedPatterns(BigInteger("1058714467799661420")))
        assertEquals(match, FixedSuffixPatterns.getMatchedPatterns(BigInteger("1058714542298891420")))
    }

    @Test
    fun `test that 911 are matched`() {
        val match = listOf(jetFuel)

        assertEquals(match, FixedSuffixPatterns.getMatchedPatterns(BigInteger("1058714467799661911")))
        assertEquals(match, FixedSuffixPatterns.getMatchedPatterns(BigInteger("1058714542298891911")))
    }

    @Test
    fun `test that 69 are matched`() {
        val match = listOf(funnySexNumber)

        assertEquals(match, FixedSuffixPatterns.getMatchedPatterns(BigInteger("1058714467799661169")))
        assertEquals(match, FixedSuffixPatterns.getMatchedPatterns(BigInteger("1058714542298891969")))
    }
}