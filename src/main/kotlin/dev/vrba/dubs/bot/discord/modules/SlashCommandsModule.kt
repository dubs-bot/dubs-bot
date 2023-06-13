package dev.vrba.dubs.bot.discord.modules

import dev.vrba.dubs.bot.discord.DiscordBotModule
import discord4j.common.util.Snowflake
import discord4j.core.GatewayDiscordClient
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent
import discord4j.core.spec.EmbedCreateSpec
import discord4j.discordjson.json.ApplicationCommandRequest
import discord4j.rest.util.Color
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.util.*

@Component
class SlashCommandsModule : DiscordBotModule {

    override fun register(client: GatewayDiscordClient): Mono<Void> {
        return runBlocking {
            val applicationId = client.restClient.applicationId.awaitSingle()
            val service = client.restClient.applicationService
            val requests = listOf(
                ApplicationCommandRequest.builder()
                    .name("leaderboard")
                    .description("Returns a link with the leaderboard for this guild")
                    .build(),
                ApplicationCommandRequest.builder()
                    .name("patterns")
                    .description("Returns a link to the listing of all recognized patterns")
                    .build(),
            )

            val handler = client.on(ChatInputInteractionEvent::class.java) {
                it.reply().withEmbeds(
                    when (it.commandName) {
                        "leaderboard" -> leaderboardEmbed(it.interaction.guildId)
                        "patterns" -> patternsEmbed()
                        else -> unknownCommandEmbed()
                    }
                )
            }

            requests.fold(handler.then()) { composition, request ->
                composition.and(
                    service.createGlobalApplicationCommand(
                        applicationId,
                        request
                    )
                )
            }
        }
    }

    private fun leaderboardEmbed(guild: Optional<Snowflake>): EmbedCreateSpec {
        val id = guild.map { it.asString() }.orElse("")
        val link = "https://dubsbot.online/leaderboards/$id"
        val embed = EmbedCreateSpec.builder()
            .url(link)
            .title("Leaderboard for this guild")
            .color(Color.of(0x3b88c3))
            .thumbnail("https://avatars.githubusercontent.com/u/133408315")
            .description(link)

        return embed.build()
    }

    private fun patternsEmbed(): EmbedCreateSpec {
        val link = "https://dubsbot.online/patterns"
        val embed = EmbedCreateSpec.builder()
            .url(link)
            .color(Color.of(0x3b88c3))
            .title("The listing of all recognized digit patterns")
            .thumbnail("https://avatars.githubusercontent.com/u/133408315")
            .description(link)

        return embed.build()
    }

    private fun unknownCommandEmbed(): EmbedCreateSpec {
        return EmbedCreateSpec.builder()
            .title("Unknown command")
            .description("This is probably a bug")
            .build()
    }
}