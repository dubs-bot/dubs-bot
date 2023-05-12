package dev.vrba.dubs.bot.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("digit_patterns")
data class DigitPattern(
    @Id
    val id: Int = 0,

    @Column("pattern_key")
    val key: String,

    @Column("pattern_name")
    val name: String,

    @Column("pattern_emoji")
    val emoji: String,

    @Column("pattern_description")
    val description: String,

    @Column("pattern_points")
    val points: Int
)