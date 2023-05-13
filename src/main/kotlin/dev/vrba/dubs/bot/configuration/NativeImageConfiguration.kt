package dev.vrba.dubs.bot.configuration

import org.springframework.aot.hint.RuntimeHints
import org.springframework.aot.hint.RuntimeHintsRegistrar
import org.springframework.context.annotation.Configuration

@Configuration
class NativeImageConfiguration : RuntimeHintsRegistrar {

    override fun registerHints(hints: RuntimeHints, classLoader: ClassLoader?) {
        hints.resources().registerPattern("schema.sql")
    }

}