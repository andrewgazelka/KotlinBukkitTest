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

        val world = server.worlds[0]

        message("$blue 1st Layer",false)
        drop(Material.STONE,Location(world, 0.0, 150.0, 0.0),Location(world, 5.0, 190.0, 5.0),doDamage = true)
        message("$blue 2st Layer",false)
        drop(Material.STONE,Location(world, 1.0, 190.0, 1.0),Location(world, 4.0, 210.0, 4.0),doDamage = true)
        message("$blue 3rd Layer",false)
        drop(Material.STONE,Location(world, 2.0, 210.0, 2.0),Location(world, 3.0, 230.0, 3.0),doDamage = true)
    }

    private fun drop(material: Material, location1: Location, location2: Location, dataLow: Int = 0, dataHigh: Int = 1, doDamage: Boolean = false) {

        for (loc in location1..location2) {
            val fallingBlock = loc.world.spawnFallingBlock(loc, material, (dataLow randTo dataHigh).toByte())
            fallingBlock.setHurtEntities(doDamage)
            message("$yellow Dropping ${material.name}! (${loc.blockX},${loc.blockY},${loc.blockZ})",false)
        }
    }

    override fun onDisable() {
        message("$red Disabled")
    }
}
