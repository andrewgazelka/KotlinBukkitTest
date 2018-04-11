package com.github.andrewgazelka.kotlintest

import org.bukkit.scheduler.BukkitRunnable


fun <E> List<E>.iteratePeriod(period: Long, delegate: (E) -> Unit) {
    object : BukkitRunnable()
    {
        val iterator: Iterator<E> = this@iteratePeriod.iterator()

        override fun run() {
            if(iterator.hasNext())
            {
                delegate.invoke(iterator.next())
            }
            else
            {
                cancel()
            }
        }
    }.runTaskTimer(KotlinTest.instance,period,period)
}

fun IntRange.iteratePeriod(period: Long, delegate: (Int) -> Unit)
{
    val iterator = this.iterator()

    object : BukkitRunnable()
    {
        override fun run() {
            if(iterator.hasNext())
            {
                delegate.invoke(iterator.next())
            }
            else
            {
                cancel()
            }
        }
    }.runTaskTimer(KotlinTest.instance,period,period)
}