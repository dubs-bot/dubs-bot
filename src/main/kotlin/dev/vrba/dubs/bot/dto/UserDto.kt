package dev.vrba.dubs.bot.dto

import dev.vrba.dubs.bot.domain.User

data class UserDto(
    val id: String,
    val name: String,
    val avatar: String
)

fun User.toDto(): UserDto {
    return UserDto(
        user.toString(),
        name,
        avatar
    )
}