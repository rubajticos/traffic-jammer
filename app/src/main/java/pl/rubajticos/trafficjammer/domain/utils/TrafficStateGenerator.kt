package pl.rubajticos.trafficjammer.domain.utils

import pl.rubajticos.trafficjammer.domain.model.TrafficSignalColor
import pl.rubajticos.trafficjammer.domain.model.TrafficSignalConfig
import pl.rubajticos.trafficjammer.domain.model.TrafficSignalState
import java.util.*
import javax.inject.Inject

class TrafficStateGenerator @Inject constructor() {

    fun calculateNextState(
        config: TrafficSignalConfig,
        state: TrafficSignalState
    ): TrafficSignalState {
        return when (state.color) {
            TrafficSignalColor.GREEN -> {
                val nextDate = calculateNextStateDate(state.changeDate, config.greenDuration)
                TrafficSignalState(
                    changeDate = nextDate,
                    color = TrafficSignalColor.GREEN
                )
            }
            TrafficSignalColor.RED -> {
                val nextDate = calculateNextStateDate(state.changeDate, config.redDuration)
                TrafficSignalState(
                    changeDate = nextDate,
                    color = TrafficSignalColor.RED
                )
            }
        }
    }

    private fun calculateNextStateDate(previousStateDate: Date, duration: Long): Date {
        val calendar = Calendar.getInstance()
        calendar.time = previousStateDate
        calendar.add(Calendar.SECOND, duration.toInt())

        return calendar.time
    }

}