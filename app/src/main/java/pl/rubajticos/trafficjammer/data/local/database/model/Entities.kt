package pl.rubajticos.trafficjammer.data.local.database.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import pl.rubajticos.trafficjammer.domain.model.JamSection
import pl.rubajticos.trafficjammer.domain.model.TrafficSignal
import pl.rubajticos.trafficjammer.domain.model.TrafficSignalColor
import pl.rubajticos.trafficjammer.domain.model.TrafficSignalState

@Entity
data class JamSectionEntity(
    @PrimaryKey(autoGenerate = true) val jamSectionId: Long = 0,
    val routeName: String
) {
    fun toDomain(trafficSignals: List<TrafficSignal>): JamSection {
        return JamSection(
            id = this.jamSectionId,
            routeName = this.routeName,
            trafficSignals = trafficSignals
        )
    }
}

@Entity
data class TrafficSignalEntity(
    @PrimaryKey(autoGenerate = true) val trafficSignalId: Long = 0,
    val directionTo: String,
    @Embedded val config: TrafficSignalConfig,
    val jamSectionOwnerId: Long? = null
) {
    fun toDomain(
        states: List<TrafficSignalState>,
        config: pl.rubajticos.trafficjammer.domain.model.TrafficSignalConfig
    ): TrafficSignal {
        return TrafficSignal(
            id = this.trafficSignalId,
            directionTo = this.directionTo,
            config = config,
            changes = states
        )
    }
}

@Entity
data class TrafficSignalStateEntity(
    @PrimaryKey(autoGenerate = true) val trafficSignalStateId: Long = 0,
    val changeDate: LocalDateTime,
    val color: String,
    val executed: Boolean,
    val trafficSignalOwnerId: Long? = null
) {
    fun toDomain(): TrafficSignalState {
        return TrafficSignalState(
            id = this.trafficSignalStateId,
            changeDate = this.changeDate,
            color = TrafficSignalColor.valueOf(this.color),
            executed = this.executed
        )
    }
}

data class TrafficSignalConfig(
    val initialGreenLightDate: LocalDateTime,
    val initialRedLightDate: LocalDateTime,
    val initialSecondGreenLightDate: LocalDateTime,
    val greenLightDuration: Long,
    val redLightDuration: Long,
) {
    fun toDomain(): pl.rubajticos.trafficjammer.domain.model.TrafficSignalConfig {
        return pl.rubajticos.trafficjammer.domain.model.TrafficSignalConfig(
            this.initialGreenLightDate,
            this.initialRedLightDate,
            this.initialSecondGreenLightDate,
            this.greenLightDuration,
            this.redLightDuration
        )
    }
}