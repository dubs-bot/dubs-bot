package dev.vrba.dubs.bot.controller

import dev.vrba.dubs.bot.dto.GuildLeaderboardDto
import dev.vrba.dubs.bot.service.LeaderboardService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.math.BigInteger

@RestController
@RequestMapping("/api/v1/leaderboard")
class LeaderboardController(private val service: LeaderboardService) {

    @GetMapping("/guild/{guild}")
    suspend fun guild(@PathVariable("guild") guild: BigInteger): ResponseEntity<GuildLeaderboardDto> {
        return service.getGuildLeaderboard(guild)
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()
    }
}