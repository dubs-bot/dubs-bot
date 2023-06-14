package dev.vrba.dubs.bot.discord.modules

import dev.kord.core.Kord
import dev.kord.core.event.gateway.ReadyEvent
import dev.kord.core.on
import dev.vrba.dubs.bot.discord.DiscordBotModule
import dev.vrba.dubs.bot.patterns.DigitsPatternRegistrar
import org.springframework.stereotype.Component

@Component
class SetupModule(private val registrar: DigitsPatternRegistrar) : DiscordBotModule {

    override suspend fun register(client: Kord) {
        client.on<ReadyEvent> {
            registrar.registerAvailablePatterns()
            client.editPresence { watching("for dubs") }
        }
    }

}