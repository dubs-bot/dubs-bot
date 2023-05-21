package dev.vrba.dubs.bot.controller

import dev.vrba.dubs.bot.dto.toDto
import dev.vrba.dubs.bot.response.GuildResponse
import dev.vrba.dubs.bot.service.GuildService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.math.BigInteger

@RestController
@RequestMapping("/api/v1/guilds")
class GuildController(private val service: GuildService) {

    @GetMapping("/user/{user}")
    suspend fun user(@PathVariable("user") user: BigInteger): ResponseEntity<GuildResponse> {
        val guilds = service.getGuildsWithUserScore(user)
        val response = GuildResponse(guilds.map { it.toDto() })

        return ResponseEntity.ok(response)
    }

}