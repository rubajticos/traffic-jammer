package pl.rubajticos.trafficjammer.domain.utils

import pl.rubajticos.trafficjammer.domain.model.TrafficSignalColor
import pl.rubajticos.trafficjammer.domain.model.TrafficSignalConfig
import pl.rubajticos.trafficjammer.domain.model.TrafficSignalState
import java.time.LocalDateTime
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
                    color = TrafficSignalColor.RED
                )
            }
            TrafficSignalColor.RED -> {
                val nextDate = calculateNextStateDate(state.changeDate, config.redDuration)
                TrafficSignalState(
                    changeDate = nextDate,
                    color = TrafficSignalColor.GREEN
                )
            }
        }
    }

    private fun calculateNextStateDate(
        previousStateDate: LocalDateTime,
        duration: Long
    ): LocalDateTime = previousStateDate.plusSeconds(duration)

}