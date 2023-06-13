package dev.vrba.dubs.bot.discord.modules

import dev.vrba.dubs.bot.discord.DiscordBotModule
import dev.vrba.dubs.bot.domain.DigitPattern
import dev.vrba.dubs.bot.patterns.DigitsPatternRegistrar
import dev.vrba.dubs.bot.service.UserScoreService
import dev.vrba.dubs.bot.util.EmojiFourLeafClover
import discord4j.core.GatewayDiscordClient
import discord4j.core.event.domain.message.MessageCreateEvent
import discord4j.core.`object`.reaction.ReactionEmoji
import discord4j.core.spec.EmbedCreateSpec
import discord4j.rest.util.Color
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import kotlin.jvm.optionals.getOrNull

@Component
class MessageDigitPatternsModule(
    private val registrar: DigitsPatternRegistrar,
    private val scoreService: UserScoreService
) : DiscordBotModule {

    @OptIn(ExperimentalStdlibApi::class)
    override fun register(client: GatewayDiscordClient): Mono<Void> {
        return client.on(MessageCreateEvent::class.java) {
            return@on runBlocking {
                val message = it.message

                val id = message.id.asBigInteger()
                val matches = registrar.matchPatterns(id)

                val author = message.author.getOrNull() ?: return@runBlocking Mono.empty()
                val guild = message.guild.awaitSingleOrNull() ?: return@runBlocking Mono.empty()

                if (matches.isEmpty() || author.isBot) {
                    return@runBlocking Mono.empty()
                }

                // Update user score in the database
                scoreService.updateScore(author, guild, matches)

                // Update user and guild info in the database
                scoreService.updateUser(author)
                scoreService.updateGuild(guild)

                // Add reactions to the message
                val base = message.addReaction(ReactionEmoji.unicode(EmojiFourLeafClover))
                val reactions = matches
                    .map { it.emoji }
                    .map { message.addReaction(ReactionEmoji.unicode(it)) }
                    .fold(base) { composition, reactions -> composition.and(reactions) }

                if (matches.any { it.isRare() }) {
                    val channel = message.channel.awaitSingle()

                    return@runBlocking reactions.and(
                        channel.createMessage(
                            EmbedCreateSpec.builder()
                                .color(Color.of(0x5865F2))
                                .title("Whoa! Those are some pog digits!")
                                .image("https://i.imgur.com/a31ZwOV.gif")
                                .description("`$id`")
                                .build()
                        )
                    ).then()
                }

                reactions.then()
            }
        }.then()
    }

    private fun DigitPattern.isRare(): Boolean {
        return this.points >= 1000
    }
}