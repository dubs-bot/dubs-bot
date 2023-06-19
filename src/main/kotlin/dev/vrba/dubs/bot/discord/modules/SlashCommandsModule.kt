package dev.vrba.dubs.bot.discord.modules

import dev.kord.common.Color
import dev.kord.common.entity.Snowflake
import dev.kord.core.Kord
import dev.kord.core.behavior.channel.asChannelOfOrNull
import dev.kord.core.behavior.interaction.respondPublic
import dev.kord.core.entity.channel.TextChannel
import dev.kord.core.event.interaction.ChatInputCommandInteractionCreateEvent
import dev.kord.core.on
import dev.kord.rest.builder.interaction.integer
import dev.kord.rest.builder.message.create.InteractionResponseCreateBuilder
import dev.kord.rest.builder.message.create.actionRow
import dev.kord.rest.builder.message.create.embed
import dev.kord.x.emoji.Emojis
import dev.vrba.dubs.bot.discord.DiscordBotModule
import dev.vrba.dubs.bot.discord.toBigInteger
import dev.vrba.dubs.bot.patterns.RepeatingDigits
import org.springframework.stereotype.Component
import java.math.BigInteger

@Component
class SlashCommandsModule : DiscordBotModule {

    override suspend fun register(client: Kord) {
        client.createGlobalChatInputCommand("leaderboard", "Returns a link with the leaderboard for this guild")
        client.createGlobalChatInputCommand("patterns", "Returns a link to the listing of all recognized patterns")
        client.createGlobalChatInputCommand("roll", "Roll 4chan style digits") {
            integer(
                "decimal_places",
                "Number of decimal places that should be rolled: eg. 1 for 0-9, 2 for 00-99 etc."
            ) {
                required = false
            }
        }

        client.on<ChatInputCommandInteractionCreateEvent> {
            interaction.respondPublic {
                when (interaction.invokedCommandName) {
                    "leaderboard" -> leaderboardCommandOutput(interaction.channel.asChannelOfOrNull<TextChannel>()?.guildId)
                    "patterns" -> patternsCommandOutput()
                    "roll" -> rollCommandOutput(
                        interaction.id.toBigInteger(),
                        interaction.command.integers["decimal_places"] ?: 2
                    )

                    else -> embed {
                        title = "Unknown command"
                        description = "Something has gone terribly wrong"
                    }
                }
            }
        }
    }

    private fun InteractionResponseCreateBuilder.leaderboardCommandOutput(guild: Snowflake?) {
        val link = "https://dubsbot.online/leaderboards/${guild?.toString().orEmpty()}"

        embed {
            url = link
            color = Color(0x3b88c3)
            title = "Leaderboard for this guild"
            thumbnail { url = "https://avatars.githubusercontent.com/u/133408315" }
        }

        actionRow {
            linkButton(link) {
                label = "Leaderboard"
            }
        }
    }

    private fun InteractionResponseCreateBuilder.patternsCommandOutput() {
        val link = "https://dubsbot.online/patterns"

        embed {
            url = link
            color = Color(0x3b88c3)
            title = "The listing of all recognized digit patterns"
            thumbnail { url = "https://avatars.githubusercontent.com/u/133408315" }
        }

        actionRow {
            linkButton(link) {
                label = "All recognized digit patterns"
            }
        }
    }

    private fun InteractionResponseCreateBuilder.rollCommandOutput(id: BigInteger, decimalPlaces: Long) {
        val dubs = RepeatingDigits.getMatchedPatterns(id).isNotEmpty()
        val emoji = if (dubs) Emojis.fourLeafClover.unicode else Emojis.shamrock.unicode

        val places = decimalPlaces.coerceIn(1L..5L).toInt()
        val rolled = id.mod(BigInteger.TEN.pow(places)).toString().padStart(places, '0')

        embed {
            color = Color(if (dubs) 0x78b454 else 0x1e1f22)
            title = "$emoji **$id**"
            description = "# $rolled"
            footer {
                text = if (dubs) "Dubs!" else "Not dubs"
            }
        }
    }
}