package pl.rubajticos.trafficjammer.domain.model

import java.time.LocalDateTime
import java.util.*

data class JamSection(
    val id: Long? = null,
    val routeName: String,
    val trafficSignals: List<TrafficSignal>
)

data class TrafficSignal(
    val id: Long? = null,
    val directionTo: String,
    val config: TrafficSignalConfig,
    val changes: List<TrafficSignalState>,
    val currentChange: TrafficSignalState? = null,
    val nextChange: TrafficSignalState? = null
)

data class TrafficSignalConfig(
    val initialGreenLight: LocalDateTime,
    val initialRedLight: LocalDateTime,
    val initialSecondGreenLight: LocalDateTime,
    val greenDuration: Long,
    val redDuration: Long
) {}

data class TrafficSignalState(
    val id: Long? = null,
    val changeDate: LocalDateTime,
    val color: TrafficSignalColor,
    val executed: Boolean = false
)

enum class TrafficSignalColor {
    GREEN, RED
}