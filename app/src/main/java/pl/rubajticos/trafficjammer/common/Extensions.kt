package pl.rubajticos.trafficjammer.common

import java.util.*
import java.util.concurrent.TimeUnit

fun Date.minusToSeconds(date: Date): Long = TimeUnit.MILLISECONDS.toSeconds(
    this.time - date.time
)