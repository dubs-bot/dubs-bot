package dev.vrba.dubs.bot.discord

import dev.kord.core.Kord

interface DiscordBotModule {

    suspend fun register(client: Kord)

}