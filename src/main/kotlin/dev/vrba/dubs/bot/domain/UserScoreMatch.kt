package dev.vrba.dubs.bot.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.math.BigInteger

@Table("user_score_matches")
data class UserScoreMatch(
    @Id
    val id: Int = 0,

    @Column("user_id")
    val user: BigInteger,

    @Column("guild_id")
    val guild: BigInteger,

    @Column("pattern_key")
    val pattern: String,

    @Column("count")
    val count: Int
)
