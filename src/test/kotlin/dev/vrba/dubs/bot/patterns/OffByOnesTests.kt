package dev.vrba.dubs.bot.patterns

import dev.vrba.dubs.bot.domain.DigitPattern
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigInteger

internal class OffByOnesTests {

    @Test
    fun `test that off-by-ones do not match singles`() {
        assertEquals(emptyList<DigitPattern>(), OffByOnes.getMatchedPatterns(BigInteger("1058714467799662643")))
        assertEquals(emptyList<DigitPattern>(), OffByOnes.getMatchedPatterns(BigInteger("1058714503686139914")))
        assertEquals(emptyList<DigitPattern>(), OffByOnes.getMatchedPatterns(BigInteger("1058714504684392550")))
        assertEquals(emptyList<DigitPattern>(), OffByOnes.getMatchedPatterns(BigInteger("1058714506802507907")))
        assertEquals(emptyList<DigitPattern>(), OffByOnes.getMatchedPatterns(BigInteger("1058714508467650570")))
        assertEquals(emptyList<DigitPattern>(), OffByOnes.getMatchedPatterns(BigInteger("1058714524292759552")))
        assertEquals(emptyList<DigitPattern>(), OffByOnes.getMatchedPatterns(BigInteger("1058714525781729291")))
        assertEquals(emptyList<DigitPattern>(), OffByOnes.getMatchedPatterns(BigInteger("1058714526905806850")))
        assertEquals(emptyList<DigitPattern>(), OffByOnes.getMatchedPatterns(BigInteger("1058714528738705408")))
        assertEquals(emptyList<DigitPattern>(), OffByOnes.getMatchedPatterns(BigInteger("1058714536439451758")))
        assertEquals(emptyList<DigitPattern>(), OffByOnes.getMatchedPatterns(BigInteger("1058714537991348295")))
        assertEquals(emptyList<DigitPattern>(), OffByOnes.getMatchedPatterns(BigInteger("1058714539396435998")))
        assertEquals(emptyList<DigitPattern>(), OffByOnes.getMatchedPatterns(BigInteger("1058714540759593071")))
        assertEquals(emptyList<DigitPattern>(), OffByOnes.getMatchedPatterns(BigInteger("1058714542298890261")))
        assertEquals(emptyList<DigitPattern>(), OffByOnes.getMatchedPatterns(BigInteger("1058714543922089984")))
        assertEquals(emptyList<DigitPattern>(), OffByOnes.getMatchedPatterns(BigInteger("1058714556773433404")))
        assertEquals(emptyList<DigitPattern>(), OffByOnes.getMatchedPatterns(BigInteger("1058714557947850812")))
        assertEquals(emptyList<DigitPattern>(), OffByOnes.getMatchedPatterns(BigInteger("1058714559042560080")))
        assertEquals(emptyList<DigitPattern>(), OffByOnes.getMatchedPatterns(BigInteger("1058714560195985498")))
        assertEquals(emptyList<DigitPattern>(), OffByOnes.getMatchedPatterns(BigInteger("1058714561236176956")))
        assertEquals(emptyList<DigitPattern>(), OffByOnes.getMatchedPatterns(BigInteger("1058714564604203058")))
    }

    @Test
    fun `test that quads are not matched`() {
        assertEquals(emptyList<DigitPattern>(), OffByOnes.getMatchedPatterns(BigInteger("1058714467799663333")))
        assertEquals(emptyList<DigitPattern>(), OffByOnes.getMatchedPatterns(BigInteger("1058714503686139999")))
        assertEquals(emptyList<DigitPattern>(), OffByOnes.getMatchedPatterns(BigInteger("1058714504684390000")))
        assertEquals(emptyList<DigitPattern>(), OffByOnes.getMatchedPatterns(BigInteger("1058714506802500000")))
        assertEquals(emptyList<DigitPattern>(), OffByOnes.getMatchedPatterns(BigInteger("1058714508467650000")))
        assertEquals(emptyList<DigitPattern>(), OffByOnes.getMatchedPatterns(BigInteger("1058714524292755555")))
        assertEquals(emptyList<DigitPattern>(), OffByOnes.getMatchedPatterns(BigInteger("1058714525781722222")))
        assertEquals(emptyList<DigitPattern>(), OffByOnes.getMatchedPatterns(BigInteger("1058714526905800000")))
        assertEquals(emptyList<DigitPattern>(), OffByOnes.getMatchedPatterns(BigInteger("1058714528738708888")))
        assertEquals(emptyList<DigitPattern>(), OffByOnes.getMatchedPatterns(BigInteger("1058714536439458888")))
        assertEquals(emptyList<DigitPattern>(), OffByOnes.getMatchedPatterns(BigInteger("1058714537991348888")))
        assertEquals(emptyList<DigitPattern>(), OffByOnes.getMatchedPatterns(BigInteger("1058714539396499999")))
        assertEquals(emptyList<DigitPattern>(), OffByOnes.getMatchedPatterns(BigInteger("1058714540759591111")))
        assertEquals(emptyList<DigitPattern>(), OffByOnes.getMatchedPatterns(BigInteger("1058714542298891111")))
        assertEquals(emptyList<DigitPattern>(), OffByOnes.getMatchedPatterns(BigInteger("1058714543922081111")))
        assertEquals(emptyList<DigitPattern>(), OffByOnes.getMatchedPatterns(BigInteger("1058714556773434444")))
        assertEquals(emptyList<DigitPattern>(), OffByOnes.getMatchedPatterns(BigInteger("1058714557947850000")))
        assertEquals(emptyList<DigitPattern>(), OffByOnes.getMatchedPatterns(BigInteger("1058714559042560000")))
        assertEquals(emptyList<DigitPattern>(), OffByOnes.getMatchedPatterns(BigInteger("1058714560195988888")))
        assertEquals(emptyList<DigitPattern>(), OffByOnes.getMatchedPatterns(BigInteger("1058714561236176666")))
        assertEquals(emptyList<DigitPattern>(), OffByOnes.getMatchedPatterns(BigInteger("1058714564604208888")))
    }

    @Test
    fun `test that off-by-one quads are not matched`() {
        assertEquals(listOf(offByOnes), OffByOnes.getMatchedPatterns(BigInteger("1058714467799663332")))
        assertEquals(listOf(offByOnes), OffByOnes.getMatchedPatterns(BigInteger("1058714503686139998")))
        assertEquals(listOf(offByOnes), OffByOnes.getMatchedPatterns(BigInteger("1058714504684390009")))
        assertEquals(listOf(offByOnes), OffByOnes.getMatchedPatterns(BigInteger("1058714506802500001")))
        assertEquals(listOf(offByOnes), OffByOnes.getMatchedPatterns(BigInteger("1058714508467650001")))
        assertEquals(listOf(offByOnes), OffByOnes.getMatchedPatterns(BigInteger("1058714524292755554")))
        assertEquals(listOf(offByOnes), OffByOnes.getMatchedPatterns(BigInteger("1058714525781722223")))
        assertEquals(listOf(offByOnes), OffByOnes.getMatchedPatterns(BigInteger("1058714526905800001")))
        assertEquals(listOf(offByOnes), OffByOnes.getMatchedPatterns(BigInteger("1058714528738708887")))
        assertEquals(listOf(offByOnes), OffByOnes.getMatchedPatterns(BigInteger("1058714536439458889")))
        assertEquals(listOf(offByOnes), OffByOnes.getMatchedPatterns(BigInteger("1058714537991348887")))
        assertEquals(listOf(offByOnes), OffByOnes.getMatchedPatterns(BigInteger("1058714539396499990")))
        assertEquals(listOf(offByOnes), OffByOnes.getMatchedPatterns(BigInteger("1058714540759591112")))
        assertEquals(listOf(offByOnes), OffByOnes.getMatchedPatterns(BigInteger("1058714542298891110")))
        assertEquals(listOf(offByOnes), OffByOnes.getMatchedPatterns(BigInteger("1058714543922081112")))
        assertEquals(listOf(offByOnes), OffByOnes.getMatchedPatterns(BigInteger("1058714556773434443")))
        assertEquals(listOf(offByOnes), OffByOnes.getMatchedPatterns(BigInteger("1058714557947850009")))
        assertEquals(listOf(offByOnes), OffByOnes.getMatchedPatterns(BigInteger("1058714559042560001")))
        assertEquals(listOf(offByOnes), OffByOnes.getMatchedPatterns(BigInteger("1058714560195988889")))
        assertEquals(listOf(offByOnes), OffByOnes.getMatchedPatterns(BigInteger("1058714561236176667")))
        assertEquals(listOf(offByOnes), OffByOnes.getMatchedPatterns(BigInteger("1058714564604208887")))
    }
}