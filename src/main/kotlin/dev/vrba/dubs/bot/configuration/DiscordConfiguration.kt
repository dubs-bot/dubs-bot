package dev.vrba.dubs.bot.configuration

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "discord")
data class DiscordConfiguration(val token: String, val apiKey: String)