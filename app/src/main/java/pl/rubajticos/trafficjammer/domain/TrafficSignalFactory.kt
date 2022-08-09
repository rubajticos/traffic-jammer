package pl.rubajticos.trafficjammer.domain

import pl.rubajticos.trafficjammer.domain.model.TrafficSignal
import pl.rubajticos.trafficjammer.domain.utils.TrafficStateGenerator
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class TrafficSignalFactory @Inject constructor(
    private val statesGenerator: TrafficStateGenerator
) {
    fun createTrafficSignal(
        directionName: String,
        greenLightDate: Date,
        redLightDate: Date,
        secondGreenLightDate: Date,
    ): TrafficSignal {
        val greenDuration = TimeUnit.MILLISECONDS.toSeconds(redLightDate.time - greenLightDate.time)
        val redDuration =
            TimeUnit.MILLISECONDS.toSeconds(secondGreenLightDate.time - redLightDate.time)


    }

}