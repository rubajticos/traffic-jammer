package pl.rubajticos.trafficjammer.domain

import pl.rubajticos.trafficjammer.common.betweenInSeconds
import pl.rubajticos.trafficjammer.domain.model.TrafficSignal
import pl.rubajticos.trafficjammer.domain.model.TrafficSignalColor
import pl.rubajticos.trafficjammer.domain.model.TrafficSignalConfig
import pl.rubajticos.trafficjammer.domain.model.TrafficSignalState
import pl.rubajticos.trafficjammer.domain.utils.TrafficStateGenerator
import java.time.LocalDateTime
import javax.inject.Inject

class TrafficSignalFactory @Inject constructor(
    private val statesGenerator: TrafficStateGenerator
) {
    suspend fun createTrafficSignal(
        directionName: String,
        greenLightDate: LocalDateTime,
        redLightDate: LocalDateTime,
        secondGreenLightDate: LocalDateTime,
    ): TrafficSignal {
        val greenDuration = redLightDate.betweenInSeconds(greenLightDate)
        val redDuration = secondGreenLightDate.betweenInSeconds(redLightDate)
        val config = TrafficSignalConfig(greenDuration, redDuration)
        val states = mutableListOf(
            TrafficSignalState(
                changeDate = secondGreenLightDate,
                color = TrafficSignalColor.GREEN,
                executed = true
            )
        )
        val tomorrow = LocalDateTime.now().plusDays(1)
        while (states.last().changeDate.isBefore(tomorrow)) {
            val nextState = statesGenerator.calculateNextState(config, states.last())
            states.add(nextState)
        }

        return TrafficSignal(
            directionTo = directionName,
            config = config,
            changes = states.toList()
        )
    }
}