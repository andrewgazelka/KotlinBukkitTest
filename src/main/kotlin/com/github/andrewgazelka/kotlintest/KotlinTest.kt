package com.github.andrewgazelka.kotlintest

import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityChangeBlockEvent
import org.bukkit.plugin.java.JavaPlugin

class KotlinTest : JavaPlugin(), Listener {

    companion object {
        lateinit var instance: KotlinTest
    }

    override fun onEnable() {
        instance = this
        Config.reload()

//        val schematicOperation = SchematicOperation(File(dataFolder, "build.schematic"))
//        schematicOperation.paste(server.worlds[0])

        server.pluginManager.registerEvents(this,this)

        val world = server.worlds[0]

        message("$blue 1st Layer",false)
        drop(Material.WOOL,Location(world, 0.0, 150.0, 0.0),Location(world, 5.0, 190.0, 5.0),dataHigh = 16,doDamage = true)
//        message("$blue 2st Layer",false)
//        dropSlow(Material.STONE,Location(world, 1.0, 190.0, 1.0),Location(world, 4.0, 210.0, 4.0),doDamage = true)
//        message("$blue 3rd Layer",false)
//        dropSlow(Material.STONE,Location(world, 2.0, 210.0, 2.0),Location(world, 3.0, 230.0, 3.0),doDamage = true)
    }

    private fun dropSlow(material: Material, location1: Location, location2: Location, dataLow: Int = 0, dataHigh: Int = 1, doDamage: Boolean = false) {

        (location1..location2).iteratePeriod(1,{
            val fallingBlock = it.world.spawnFallingBlock(it, material, (dataLow randTo dataHigh).toByte())
            fallingBlock.setHurtEntities(doDamage)
            fallingBlock.onDeath {
                val location3 = fallingBlock.location
                location3.world.createExplosion(location3,4.0F)
            }
            message("$yellow Dropping ${material.name}! (${it.blockX},${it.blockY},${it.blockZ})",false)
        })
    }

    private fun drop(material: Material, location1: Location, location2: Location, dataLow: Int = 0, dataHigh: Int = 1, doDamage: Boolean = false) {

        for(loc in location1..location2){
            val fallingBlock = loc.world.spawnFallingBlock(loc, material, (dataLow randTo dataHigh).toByte())
            fallingBlock.setHurtEntities(doDamage)
            fallingBlock.onDeath {
                val location3 = fallingBlock.location
                location3.world.createExplosion(location3,4.0F)
            }
            message("$yellow Dropping ${material.name}! (${loc.blockX},${loc.blockY},${loc.blockZ})",false)
        }
    }

    @EventHandler
    fun entityDeath(event: EntityChangeBlockEvent)
    {
        val entity = event.entity
        entityDeaths.remove(entity.entityId)?.invoke()
    }

    override fun onDisable() {
        message("$red Disabled")
    }
}
