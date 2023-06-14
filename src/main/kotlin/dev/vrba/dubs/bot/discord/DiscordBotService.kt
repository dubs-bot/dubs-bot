package dev.vrba.dubs.bot.discord

import dev.vrba.dubs.bot.configuration.DiscordConfiguration
import discord4j.core.DiscordClient
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
            val login = DiscordClient.create(configuration.token).withGateway { gateway ->
                modules.map { it.register(gateway) }
                    .reduce { composition, registration -> composition.and(registration) }
            }

            login.block()
        }
    }
}