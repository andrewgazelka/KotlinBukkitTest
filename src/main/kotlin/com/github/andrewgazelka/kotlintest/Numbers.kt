package com.github.andrewgazelka.kotlintest

import java.util.*

val rand = Random()

infix fun Int.to(to: Int): IntProgression {
    if (this < to) {
        return IntProgression.fromClosedRange(this, to, 1)
    }
    return IntProgression.fromClosedRange(this, to, -1)
}

infix fun Int.randTo(to: Int): Int {
    return rand.nextInt(to+1-this)+this
}