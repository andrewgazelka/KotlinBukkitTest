package com.github.andrewgazelka.kotlintest

import org.bukkit.plugin.java.JavaPlugin

class KotlinTest : JavaPlugin() {

    companion object {
        lateinit var instance: KotlinTest
    }

    override fun onEnable() {
        instance = this
        Config.reload()
        server.scheduler.scheduleSyncDelayedTask(this, { message("$green Enabled") }, Config.period_tick.toLong())
    }

    override fun onDisable() {
        message("$red Disabled")
    }
}