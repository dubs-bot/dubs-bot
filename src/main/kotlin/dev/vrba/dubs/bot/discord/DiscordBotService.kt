package dev.vrba.dubs.bot.discord

import dev.kord.core.Kord
import dev.vrba.dubs.bot.configuration.DiscordConfiguration
import jakarta.annotation.PostConstruct
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Service

@Service
class DiscordBotService(
    private val configuration: DiscordConfiguration,
    private val modules: List<DiscordBotModule>
) {
    @Bean
    fun startClient(): CommandLineRunner {
        return CommandLineRunner {
            runBlocking {
                val client = Kord(configuration.token)

                modules.map { module -> async { module.register(client) } }.awaitAll()

                client.login()
            }
        }
    }
}