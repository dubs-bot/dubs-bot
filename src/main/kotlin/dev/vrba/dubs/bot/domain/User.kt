package dev.vrba.dubs.bot.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.math.BigInteger

@Table("users")
data class User(
    @Id
    val id: Int = 0,

    @Column("user_id")
    val user: BigInteger,

    @Column("user_name")
    val name: String,

    @Column("user_avatar")
    val avatar: String
)
