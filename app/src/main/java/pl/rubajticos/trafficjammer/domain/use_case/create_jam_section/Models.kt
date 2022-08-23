package pl.rubajticos.trafficjammer.domain.use_case.create_jam_section

import java.time.LocalDateTime

data class CreateJamSectionCommand(
    val routeName: String,
    val signalsDetails: List<TrafficSignalDetail>
)

data class TrafficSignalDetail(
    val directionTo: String,
    val greenLightDate: LocalDateTime,
    val redLightDate: LocalDateTime,
    val secondGreenLightDate: LocalDateTime
)