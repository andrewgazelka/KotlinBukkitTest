package com.github.andrewgazelka.kotlintest

import org.bukkit.ChatColor

val red = ChatColor.RED
val green = ChatColor.GREEN
val yellow = ChatColor.YELLOW

fun message(message: String) {
    KotlinTest.instance.server.broadcastMessage(message)
}
