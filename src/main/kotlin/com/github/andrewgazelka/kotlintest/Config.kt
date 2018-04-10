package com.github.andrewgazelka.kotlintest

import com.github.andrewgazelka.kotlintest.Config.cm
import java.io.File

object Config {

    private val file = File("config.yml")
    val cm = ConfigManager(file)

    var period_tick = 1
    var start_message = "Hello"


    fun reload() {
        cm.load()
        period_tick = period_tick.gds("kotlin_test.timer.period_ticks")
        start_message = start_message.gds("kotlin_test.message.start")
        cm.save()
    }
}

/**
 * Stands for getDNESet
 */
private fun Int.gds(s: String): Int {
    return cm.getDNESet(s,this).result
}

/**
 * Stands for getDNESet
 */
private fun String.gds(s: String): String {
    return cm.getDNESet(s,this).result
}
