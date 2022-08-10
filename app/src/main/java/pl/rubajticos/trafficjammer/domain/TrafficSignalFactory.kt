package pl.rubajticos.trafficjammer.domain

import pl.rubajticos.trafficjammer.common.minusToSeconds
import pl.rubajticos.trafficjammer.domain.model.TrafficSignal
import pl.rubajticos.trafficjammer.domain.model.TrafficSignalColor
import pl.rubajticos.trafficjammer.domain.model.TrafficSignalConfig
import pl.rubajticos.trafficjammer.domain.model.TrafficSignalState
import pl.rubajticos.trafficjammer.domain.utils.TrafficStateGenerator
import java.util.*
import javax.inject.Inject

class TrafficSignalFactory @Inject constructor(
    private val statesGenerator: TrafficStateGenerator
) {
    suspend fun createTrafficSignal(
        directionName: String,
        greenLightDate: Date,
        redLightDate: Date,
        secondGreenLightDate: Date,
    ): TrafficSignal {
        val greenDuration = redLightDate.minusToSeconds(greenLightDate)
        val redDuration = secondGreenLightDate.minusToSeconds(redLightDate)
        val config = TrafficSignalConfig(greenDuration, redDuration)
        val states = mutableListOf(
            TrafficSignalState(
                changeDate = secondGreenLightDate,
                color = TrafficSignalColor.GREEN,
                executed = true
            )
        )
        repeat(INITIAL_NUM_OF_STATES) {
            val nextState = statesGenerator.calculateNextState(config, states.last())
            states.add(nextState)
        }

        return TrafficSignal(
            directionTo = directionName,
            config = config,
            changes = states.toList()
        )
    }


    companion object {
        private const val INITIAL_NUM_OF_STATES = 100
    }
}