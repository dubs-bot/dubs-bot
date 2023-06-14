package dev.vrba.dubs.bot.discord.modules

import dev.kord.common.Color
import dev.kord.common.entity.Snowflake
import dev.kord.core.Kord
import dev.kord.core.behavior.channel.asChannelOf
import dev.kord.core.behavior.channel.asChannelOfOrNull
import dev.kord.core.behavior.interaction.respondPublic
import dev.kord.core.entity.channel.TextChannel
import dev.kord.core.event.interaction.ChatInputCommandInteractionCreateEvent
import dev.kord.core.event.interaction.GlobalChatInputCommandInteractionCreateEvent
import dev.kord.core.on
import dev.kord.rest.builder.message.create.InteractionResponseCreateBuilder
import dev.kord.rest.builder.message.create.actionRow
import dev.kord.rest.builder.message.create.embed
import dev.vrba.dubs.bot.discord.DiscordBotModule
import org.springframework.stereotype.Component

@Component
class SlashCommandsModule : DiscordBotModule {

    override suspend fun register(client: Kord) {
        client.createGlobalChatInputCommand("leaderboard", "Returns a link with the leaderboard for this guild")
        client.createGlobalChatInputCommand("patterns", "Returns a link to the listing of all recognized patterns")

        client.on<ChatInputCommandInteractionCreateEvent> {
            interaction.respondPublic {
                when (interaction.invokedCommandName) {
                    "leaderboard" -> leaderboardCommandOutput(interaction.channel.asChannelOfOrNull<TextChannel>()?.guildId)
                    "patterns" -> patternsCommandOutput()
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
}