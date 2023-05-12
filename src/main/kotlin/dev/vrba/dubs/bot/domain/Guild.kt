package dev.vrba.dubs.bot.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.math.BigInteger

@Table("guilds")
data class Guild(
    @Id
    val id: Int = 0,

    @Column("guild_id")
    val guild: BigInteger,

    @Column("guild_name")
    val name: String,

    @Column("guild_name")
    val icon: String
)