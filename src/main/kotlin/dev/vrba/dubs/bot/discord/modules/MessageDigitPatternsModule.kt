package dev.vrba.dubs.bot.discord.modules

import dev.kord.core.Kord
import dev.kord.core.event.message.MessageCreateEvent
import dev.kord.core.on
import dev.kord.x.emoji.DiscordEmoji
import dev.kord.x.emoji.Emojis
import dev.kord.x.emoji.addReaction
import dev.vrba.dubs.bot.discord.DiscordBotModule
import dev.vrba.dubs.bot.patterns.DigitsPatternRegistrar
import org.springframework.stereotype.Component
import java.math.BigInteger

@Component
class MessageDigitPatternsModule(private val registrar: DigitsPatternRegistrar) : DiscordBotModule {

    override suspend fun register(client: Kord) {
        client.on<MessageCreateEvent> {
            val id = BigInteger(message.id.toString())
            val matches = registrar.matchPatterns(id)

            if (matches.isEmpty()) {
                return@on
            }

            listOf(Emojis.fourLeafClover.unicode)
                .plus(matches.map { it.emoji })
                .map { DiscordEmoji.Generic(it) }
                .forEach {
                    message.addReaction(it)
                }
        }
    }
}