package com.github.andrewgazelka.kotlintest

import java.io.File

object Config {

    private val file = File("config.yml")
    private val cm = ConfigManager(file)

    var period_tick = 1 //TODO: this is quite bad... a default value shouldn't need to be set

    fun reload() {
        cm.load()
        period_tick = cm.getDNESet("kotlintest.timer.period_ticks", 1).result
        cm.save()
    }
}