package dev.vrba.dubs.bot.dto

import dev.vrba.dubs.bot.domain.DigitPattern

data class DigitPatternDto(
    val key: String,
    val name: String,
    val emoji: String,
    val description: String,
    val points: Int
)

fun DigitPattern.toDto(): DigitPatternDto {
    return DigitPatternDto(key, name, emoji, description, points)
}

