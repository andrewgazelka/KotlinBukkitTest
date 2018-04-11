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
        drop(Material.TNT,doDamage = true)
    }

    private fun drop(material: Material, dataLow: Int = 0, dataHigh: Int = 1, doDamage: Boolean = false) {

        val world = server.worlds[0]

        val location1 = Location(world, 0.0, 200.0, 0.0)
        val location2 = Location(world, 5.0, 210.0, 5.0)

        for (loc in location1..location2) {
            val fallingBlock = world.spawnFallingBlock(loc, material, (dataLow randTo dataHigh).toByte())
            fallingBlock.setHurtEntities(doDamage)
            message("$yellow Dropping ${material.name}! (${loc.blockX},${loc.blockY},${loc.blockZ})")
        }
    }

    override fun onDisable() {
        message("$red Disabled")
    }
}
