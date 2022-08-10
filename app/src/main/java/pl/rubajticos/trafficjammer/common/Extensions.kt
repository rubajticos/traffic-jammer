package pl.rubajticos.trafficjammer.common

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

fun LocalDateTime.betweenInSeconds(date: LocalDateTime): Long =
    ChronoUnit.SECONDS.between(date, this)