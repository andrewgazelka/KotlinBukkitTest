package com.github.andrewgazelka.kotlintest

import org.bukkit.ChatColor
import java.util.logging.Level

val red = ChatColor.RED
val green = ChatColor.GREEN
val yellow = ChatColor.YELLOW
val blue = ChatColor.BLUE

fun message(message: String, public: Boolean = true) {
    if(public)
    {
        KotlinTest.instance.server.broadcastMessage(message)
    }
    else{
        KotlinTest.instance.logger.log(Level.INFO,message)
    }
}
