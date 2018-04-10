package com.github.andrewgazelka.kotlintest

import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.plugin.java.JavaPlugin

class KotlinTest : JavaPlugin() {

    companion object {
        lateinit var instance: KotlinTest
    }

    override fun onEnable() {
        instance = this
        Config.reload()
        dropWool()
    }

    private fun dropWool() {

        val world = server.worlds[0]

        val location1 = Location(world, 0.0, 200.0, 0.0)
        val location2 = Location(world, 5.0, 210.0, 5.0)

        for (loc in location1..location2) {
            world.spawnFallingBlock(loc, Material.WOOL, (0 randTo 15).toByte())
            message("$yellow Dropping wool! (${loc.blockX},${loc.blockY},${loc.blockZ})")
        }
    }

    override fun onDisable() {
        message("$red Disabled")
    }
}
