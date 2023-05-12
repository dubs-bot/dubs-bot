package dev.vrba.dubs.bot

import dev.vrba.dubs.bot.configuration.DiscordConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(DiscordConfiguration::class)
class DubsBotApplication

fun main(args: Array<String>) {
    runApplication<DubsBotApplication>(*args)
}
