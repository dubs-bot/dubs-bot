package dev.vrba.dubs.bot.discord.modules

import dev.vrba.dubs.bot.discord.DiscordBotModule
import dev.vrba.dubs.bot.patterns.DigitsPatternRegistrar
import discord4j.core.GatewayDiscordClient
import discord4j.core.event.domain.lifecycle.ReadyEvent
import discord4j.core.`object`.presence.ClientActivity
import discord4j.core.`object`.presence.ClientPresence
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class SetupModule(private val registrar: DigitsPatternRegistrar) : DiscordBotModule {

    override fun register(client: GatewayDiscordClient): Mono<Void> {
        val activity = ClientActivity.watching("for dubs")
        val presence = ClientPresence.online(activity)
        val handler = client.updatePresence(presence)

        return handler.then(Mono.fromRunnable { registrar.registerAvailablePatterns() })
    }

}