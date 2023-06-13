package dev.vrba.dubs.bot.discord

import discord4j.core.GatewayDiscordClient
import reactor.core.publisher.Mono

interface DiscordBotModule {

    fun register(client: GatewayDiscordClient): Mono<Void>

}