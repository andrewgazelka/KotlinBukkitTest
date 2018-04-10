package com.github.andrewgazelka.kotlintest

import org.bukkit.plugin.java.JavaPlugin

object KotlinTest : JavaPlugin() {
    override fun onEnable() {
        Config.reload()
        message("$green Enabled")
    }

    override fun onDisable() {
        message("$red Disabled")
    }
}