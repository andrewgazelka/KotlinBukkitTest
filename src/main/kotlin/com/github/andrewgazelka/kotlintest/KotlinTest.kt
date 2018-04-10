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
        server.scheduler.scheduleSyncDelayedTask(this, { message("$green Enabled") }, Config.period_tick.toLong())
        dropHay()
    }

    private fun dropHay() {
        val world = server.worlds[0]
        val location1 = Location(world, 0.0, 200.0, 0.0)
        val location2 = Location(world, 5.0, 210.0, 5.0)
        for (loc in location1..location2) {
            world.spawnFallingBlock(loc, Material.HAY_BLOCK,0)
            message("$yellow Dropping hay! (${loc.blockX},${loc.blockY},${loc.blockZ})")
        }
    }

    override fun onDisable() {
        message("$red Disabled")
    }
}

private operator fun Location.rangeTo(location2: Location): List<Location> {
    val list = ArrayList<Location>()
    for (x in this.blockX to location2.blockX) {
        for (y in this.blockY to location2.blockY) {
            for (z in this.blockZ to location2.blockZ) {
                list.add(Location(this.world, x.toDouble(), y.toDouble(), z.toDouble()))
            }
        }
    }
    return list
}

infix fun Int.to(to: Int): IntProgression {
    if (this < to) {
        return IntProgression.fromClosedRange(this, to, 1)
    }
    return IntProgression.fromClosedRange(this, to, -1)
}
