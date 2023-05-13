package dev.vrba.dubs.bot.patterns

import dev.vrba.dubs.bot.domain.DigitPattern
import java.math.BigInteger

interface DigitPatternsProvider {

    fun getAvailablePatterns(): List<DigitPattern>

    fun getMatchedPatterns(id: BigInteger): List<DigitPattern>

}