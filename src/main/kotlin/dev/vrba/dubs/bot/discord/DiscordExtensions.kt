package dev.vrba.dubs.bot.discord

import dev.kord.common.entity.Snowflake
import java.math.BigInteger

fun Snowflake.toBigInteger(): BigInteger = BigInteger(this.value.toString())