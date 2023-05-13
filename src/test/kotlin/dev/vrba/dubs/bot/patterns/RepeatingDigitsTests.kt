package dev.vrba.dubs.bot.patterns

import dev.vrba.dubs.bot.domain.DigitPattern
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigInteger

class RepeatingDigitsTests {

    @Test
    fun `test that singles are not matched`() {
        assertEquals(emptyList<DigitPattern>(), RepeatingDigits.getMatchedPatterns(BigInteger("1058714467799662643")))
        assertEquals(emptyList<DigitPattern>(), RepeatingDigits.getMatchedPatterns(BigInteger("1058714503686139914")))
        assertEquals(emptyList<DigitPattern>(), RepeatingDigits.getMatchedPatterns(BigInteger("1058714504684392550")))
        assertEquals(emptyList<DigitPattern>(), RepeatingDigits.getMatchedPatterns(BigInteger("1058714506802507907")))
        assertEquals(emptyList<DigitPattern>(), RepeatingDigits.getMatchedPatterns(BigInteger("1058714508467650570")))
        assertEquals(emptyList<DigitPattern>(), RepeatingDigits.getMatchedPatterns(BigInteger("1058714524292759552")))
        assertEquals(emptyList<DigitPattern>(), RepeatingDigits.getMatchedPatterns(BigInteger("1058714525781729291")))
        assertEquals(emptyList<DigitPattern>(), RepeatingDigits.getMatchedPatterns(BigInteger("1058714526905806850")))
        assertEquals(emptyList<DigitPattern>(), RepeatingDigits.getMatchedPatterns(BigInteger("1058714528738705408")))
        assertEquals(emptyList<DigitPattern>(), RepeatingDigits.getMatchedPatterns(BigInteger("1058714536439451758")))
        assertEquals(emptyList<DigitPattern>(), RepeatingDigits.getMatchedPatterns(BigInteger("1058714537991348295")))
        assertEquals(emptyList<DigitPattern>(), RepeatingDigits.getMatchedPatterns(BigInteger("1058714539396435998")))
        assertEquals(emptyList<DigitPattern>(), RepeatingDigits.getMatchedPatterns(BigInteger("1058714540759593071")))
        assertEquals(emptyList<DigitPattern>(), RepeatingDigits.getMatchedPatterns(BigInteger("1058714542298890261")))
        assertEquals(emptyList<DigitPattern>(), RepeatingDigits.getMatchedPatterns(BigInteger("1058714543922089984")))
        assertEquals(emptyList<DigitPattern>(), RepeatingDigits.getMatchedPatterns(BigInteger("1058714556773433404")))
        assertEquals(emptyList<DigitPattern>(), RepeatingDigits.getMatchedPatterns(BigInteger("1058714557947850812")))
        assertEquals(emptyList<DigitPattern>(), RepeatingDigits.getMatchedPatterns(BigInteger("1058714559042560080")))
        assertEquals(emptyList<DigitPattern>(), RepeatingDigits.getMatchedPatterns(BigInteger("1058714560195985498")))
        assertEquals(emptyList<DigitPattern>(), RepeatingDigits.getMatchedPatterns(BigInteger("1058714561236176956")))
        assertEquals(emptyList<DigitPattern>(), RepeatingDigits.getMatchedPatterns(BigInteger("1058714564604203058")))
    }

    @Test
    fun `test that dubs are matched`() {
        assertEquals(listOf(dubs), RepeatingDigits.getMatchedPatterns(BigInteger("1058714467799662644")))
        assertEquals(listOf(dubs), RepeatingDigits.getMatchedPatterns(BigInteger("1058714503686139944")))
        assertEquals(listOf(dubs), RepeatingDigits.getMatchedPatterns(BigInteger("1058714504684392533")))
        assertEquals(listOf(dubs), RepeatingDigits.getMatchedPatterns(BigInteger("1058714506802507977")))
        assertEquals(listOf(dubs), RepeatingDigits.getMatchedPatterns(BigInteger("1058714508467650577")))
        assertEquals(listOf(dubs), RepeatingDigits.getMatchedPatterns(BigInteger("1058714524292759533")))
        assertEquals(listOf(dubs), RepeatingDigits.getMatchedPatterns(BigInteger("1058714525781729211")))
        assertEquals(listOf(dubs), RepeatingDigits.getMatchedPatterns(BigInteger("1058714526905806855")))
        assertEquals(listOf(dubs), RepeatingDigits.getMatchedPatterns(BigInteger("1058714528738705488")))
        assertEquals(listOf(dubs), RepeatingDigits.getMatchedPatterns(BigInteger("1058714536439451788")))
        assertEquals(listOf(dubs), RepeatingDigits.getMatchedPatterns(BigInteger("1058714537991348255")))
        assertEquals(listOf(dubs), RepeatingDigits.getMatchedPatterns(BigInteger("1058714539396435988")))
        assertEquals(listOf(dubs), RepeatingDigits.getMatchedPatterns(BigInteger("1058714540759593077")))
        assertEquals(listOf(dubs), RepeatingDigits.getMatchedPatterns(BigInteger("1058714542298890211")))
        assertEquals(listOf(dubs), RepeatingDigits.getMatchedPatterns(BigInteger("1058714543922089944")))
        assertEquals(listOf(dubs), RepeatingDigits.getMatchedPatterns(BigInteger("1058714556773433344")))
        assertEquals(listOf(dubs), RepeatingDigits.getMatchedPatterns(BigInteger("1058714557947850811")))
        assertEquals(listOf(dubs), RepeatingDigits.getMatchedPatterns(BigInteger("1058714559042560088")))
        assertEquals(listOf(dubs), RepeatingDigits.getMatchedPatterns(BigInteger("1058714560195985488")))
        assertEquals(listOf(dubs), RepeatingDigits.getMatchedPatterns(BigInteger("1058714561236176966")))
        assertEquals(listOf(dubs), RepeatingDigits.getMatchedPatterns(BigInteger("1058714564604203088")))
    }

    @Test
    fun `test that trips are matched`() {
        assertEquals(listOf(trips), RepeatingDigits.getMatchedPatterns(BigInteger("1058714467799662444")))
        assertEquals(listOf(trips), RepeatingDigits.getMatchedPatterns(BigInteger("1058714503686139444")))
        assertEquals(listOf(trips), RepeatingDigits.getMatchedPatterns(BigInteger("1058714504684392555")))
        assertEquals(listOf(trips), RepeatingDigits.getMatchedPatterns(BigInteger("1058714506802509777")))
        assertEquals(listOf(trips), RepeatingDigits.getMatchedPatterns(BigInteger("1058714508467650777")))
        assertEquals(listOf(trips), RepeatingDigits.getMatchedPatterns(BigInteger("1058714524292759111")))
        assertEquals(listOf(trips), RepeatingDigits.getMatchedPatterns(BigInteger("1058714525781729111")))
        assertEquals(listOf(trips), RepeatingDigits.getMatchedPatterns(BigInteger("1058714526905806555")))
        assertEquals(listOf(trips), RepeatingDigits.getMatchedPatterns(BigInteger("1058714528738705888")))
        assertEquals(listOf(trips), RepeatingDigits.getMatchedPatterns(BigInteger("1058714536439451888")))
        assertEquals(listOf(trips), RepeatingDigits.getMatchedPatterns(BigInteger("1058714537991348555")))
        assertEquals(listOf(trips), RepeatingDigits.getMatchedPatterns(BigInteger("1058714539396435888")))
        assertEquals(listOf(trips), RepeatingDigits.getMatchedPatterns(BigInteger("1058714540759593777")))
        assertEquals(listOf(trips), RepeatingDigits.getMatchedPatterns(BigInteger("1058714542298890111")))
        assertEquals(listOf(trips), RepeatingDigits.getMatchedPatterns(BigInteger("1058714543922089444")))
        assertEquals(listOf(trips), RepeatingDigits.getMatchedPatterns(BigInteger("1058714556773433444")))
        assertEquals(listOf(trips), RepeatingDigits.getMatchedPatterns(BigInteger("1058714557947850111")))
        assertEquals(listOf(trips), RepeatingDigits.getMatchedPatterns(BigInteger("1058714559042560888")))
        assertEquals(listOf(trips), RepeatingDigits.getMatchedPatterns(BigInteger("1058714560195985888")))
        assertEquals(listOf(trips), RepeatingDigits.getMatchedPatterns(BigInteger("1058714561236178666")))
        assertEquals(listOf(trips), RepeatingDigits.getMatchedPatterns(BigInteger("1058714564604203888")))
    }
}