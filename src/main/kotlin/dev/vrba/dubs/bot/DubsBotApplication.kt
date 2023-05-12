package dev.vrba.dubs.bot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DubsBotApplication

fun main(args: Array<String>) {
    runApplication<DubsBotApplication>(*args)
}
