package com.github.andrewgazelka.kotlintest

import org.bukkit.Location
import java.util.*

operator fun Location.rangeTo(location2: Location): List<Location> {
    val list = ArrayList<Location>()
    for (y in this.blockY to location2.blockY) {
        for (x in this.blockX to location2.blockX) {
            for (z in this.blockZ to location2.blockZ) {
                list.add(Location(this.world, x.toDouble(), y.toDouble(), z.toDouble()))
            }
        }
    }
    return list
}