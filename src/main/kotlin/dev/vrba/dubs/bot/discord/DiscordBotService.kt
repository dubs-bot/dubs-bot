package dev.vrba.dubs.bot.discord

import dev.kord.common.entity.PresenceStatus
import dev.kord.core.Kord
import dev.vrba.dubs.bot.configuration.DiscordConfiguration
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Service

@Service
class DiscordBotService(
    private val configuration: DiscordConfiguration,
    private val modules: List<DiscordBotModule>
) : CommandLineRunner {

    override fun run(vararg args: String?) {
        runBlocking {
            val client = Kord(configuration.token)

            client.login()
            client.editPresence { watching("for dubs") }

            modules.map { module -> async { module.register(client) } }.awaitAll()
        }
    }

}