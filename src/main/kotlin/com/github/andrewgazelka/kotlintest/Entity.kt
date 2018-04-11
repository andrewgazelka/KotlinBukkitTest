package com.github.andrewgazelka.kotlintest

import org.bukkit.entity.Entity

val entityDeaths: MutableMap<Int, () -> Unit> = HashMap()

fun Entity.onDeath(function: () -> Unit) {
    entityDeaths[entityId] = function
}
