package pl.rubajticos.trafficjammer.model

import java.util.*

data class JamSection(
    val routeName: String,
    val trafficSignals: List<TrafficSignal>
)

data class TrafficSignal(
    val directionTo: String,
    val config: TrafficSignalConfig,
    val changes: List<TrafficSignalState>,
    val currentChange: TrafficSignalState? = null,
    val nextChange: TrafficSignalState? = null
)

data class TrafficSignalConfig(
    val greenDuration: Int,
    val redDuration: Int,
    val greenLightPatternTime: Date,
    val redLightPatternTime: Date,
)

data class TrafficSignalState(
    val changeDate: Date,
    val color: TrafficSignalColor,
    val executed: Boolean = false
)

enum class TrafficSignalColor {
    GREEN, RED
}