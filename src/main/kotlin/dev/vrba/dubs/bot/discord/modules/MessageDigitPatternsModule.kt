package dev.vrba.dubs.bot.discord.modules

import dev.kord.common.Color
import dev.kord.core.Kord
import dev.kord.core.behavior.reply
import dev.kord.core.event.message.MessageCreateEvent
import dev.kord.core.on
import dev.kord.rest.builder.message.create.embed
import dev.kord.x.emoji.DiscordEmoji
import dev.kord.x.emoji.addReaction
import dev.vrba.dubs.bot.discord.DiscordBotModule
import dev.vrba.dubs.bot.discord.toBigInteger
import dev.vrba.dubs.bot.domain.DigitPattern
import dev.vrba.dubs.bot.patterns.DigitsPatternRegistrar
import dev.vrba.dubs.bot.service.UserScoreService
import dev.vrba.dubs.bot.util.EmojiFourLeafClover
import org.springframework.stereotype.Component

@Component
class MessageDigitPatternsModule(
    private val registrar: DigitsPatternRegistrar,
    private val scoreService: UserScoreService
) : DiscordBotModule {

    override suspend fun register(client: Kord) {
        client.on<MessageCreateEvent> {
            val id = message.id.toBigInteger()
            val matches = registrar.matchPatterns(id)

            val author = message.author ?: return@on
            val guild = message.getGuildOrNull() ?: return@on

            if (matches.isEmpty() || author.isBot) {
                return@on
            }

            // Add reactions to the message
            listOf(EmojiFourLeafClover)
                .plus(matches.map { it.emoji })
                .map { DiscordEmoji.Generic(it) }
                .forEach {
                    message.addReaction(it)
                }

            if (matches.any { it.isRare() }) {
                message.reply {
                    embed {
                        color = Color(0x5865F2)
                        title = "Whoa! Those are some pog digits!"
                        description = "`$id`"
                        image = "https://i.imgur.com/a31ZwOV.gif"
                    }
                }
            }

            // Update user score in the database
            scoreService.updateScore(author, guild, matches)

            // Update user and guild info in the database
            scoreService.updateUser(author)
            scoreService.updateGuild(guild)
        }
    }

    private fun DigitPattern.isRare(): Boolean {
        return this.points >= 1000
    }
}