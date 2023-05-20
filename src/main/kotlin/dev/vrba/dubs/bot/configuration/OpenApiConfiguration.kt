package dev.vrba.dubs.bot.configuration

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.SpecVersion
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import io.swagger.v3.oas.models.servers.Server
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfiguration {

    @Bean
    fun api(): OpenAPI {
        return OpenAPI()
            .info(
                Info().apply {
                    title("Dubs bot API")
                    description("API for retrieving information and leaderboards about the running instance of dubs bot")
                    contact = Contact().url("https://github.com/dubs-bot/dubs-bot")
                }
            )
            .servers(
                listOf(
                    Server().url("https://gateway.dubsbot.online").description("Production"),
                    Server().url("http://localhost:8080").description("Development"),
                )
            )
    }
}