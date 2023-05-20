package dev.vrba.dubs.bot.dto

import dev.vrba.dubs.bot.domain.Guild

data class GuildDto(
    val id: String,
    val name: String,
    val icon: String
)

fun Guild.toDto(): GuildDto {
    return GuildDto(
        id.toString(),
        name,
        icon
    )
}