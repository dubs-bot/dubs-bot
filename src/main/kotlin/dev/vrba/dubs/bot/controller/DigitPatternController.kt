package dev.vrba.dubs.bot.controller

import dev.vrba.dubs.bot.dto.toDto
import dev.vrba.dubs.bot.response.DigitPatternsResponse
import dev.vrba.dubs.bot.service.DigitPatternService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/patterns")
class DigitPatternController(private val service: DigitPatternService) {

    @GetMapping
    suspend fun listPatterns(): ResponseEntity<DigitPatternsResponse> {
        val patterns = service.getAllPatterns().map { it.toDto() }
        val response = DigitPatternsResponse(patterns)

        return ResponseEntity.ok(response)
    }

}