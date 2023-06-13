package dev.vrba.dubs.bot.discord

import dev.vrba.dubs.bot.configuration.DiscordConfiguration
import discord4j.core.DiscordClient
import discord4j.gateway.intent.Intent
import discord4j.gateway.intent.IntentSet
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Service

@Service
class DiscordBotService(
    private val configuration: DiscordConfiguration, private val modules: List<DiscordBotModule>
) {
    @Bean
    fun startClient(): CommandLineRunner {
        return CommandLineRunner {
            DiscordClient.create(configuration.token)
                .gateway()
                .setEnabledIntents(IntentSet.of(
                    Intent.GUILDS,
                    Intent.GUILD_MESSAGES,
                    Intent.GUILD_MESSAGE_REACTIONS,
                ))
                .login()
                .flatMap { gateway ->
                    modules.map { it.register(gateway) }
                        .reduce { composition, registration -> composition.and(registration) }
                }
                .block()
        }
    }
}