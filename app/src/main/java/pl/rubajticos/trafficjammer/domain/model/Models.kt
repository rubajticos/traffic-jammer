package pl.rubajticos.trafficjammer.domain.model

import java.time.LocalDateTime
import java.util.*

data class JamSection(
    val id: Int? = null,
    val routeName: String,
    val trafficSignals: List<TrafficSignal>
)

data class TrafficSignal(
    val id: Int? = null,
    val directionTo: String,
    val config: TrafficSignalConfig,
    val changes: List<TrafficSignalState>,
    val currentChange: TrafficSignalState? = null,
    val nextChange: TrafficSignalState? = null
)

data class TrafficSignalConfig(
    val greenDuration: Long,
    val redDuration: Long
)

data class TrafficSignalState(
    val id: Int? = null,
    val changeDate: LocalDateTime,
    val color: TrafficSignalColor,
    val executed: Boolean = false
)

enum class TrafficSignalColor {
    GREEN, RED
}